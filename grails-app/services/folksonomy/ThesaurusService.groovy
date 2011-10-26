package folksonomy

import org.springframework.beans.factory.InitializingBean
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class ThesaurusService implements InitializingBean {
    static transactional = true
    static expose = ['jmx']

    String remoteServiceUri
    String apiKey
    def count = 0
    def timer
    List exceptions = []

    void afterPropertiesSet() {
        remoteServiceUri = CH.config.thesaurusService.remoteServiceUri
        apiKey = CH.config.thesaurusService.apiKey
    }

    /**
     * List of maps [[word:val1,category:val2,relationship:val3], ... ]]
     * @param word
     * @return
     */
    def wordList(word) {
        wordList(word,remoteCall)
    }

    def remoteCall = { word ->
        count++
        "${remoteServiceUri}/${apiKey}/${word}/xml"?.toURL()?.text
    }

    def wordList(word,Closure remoteLogic) {
        def start = System.nanoTime()
        def list = []
        try {
            list = parseResponse( remoteLogic.call(word.toString().replaceAll(/\s/,"%20")) )
            // TODO: don't do this exception swallow nastiness here...
        } catch(Throwable t) {
            t.printStackTrace()
            exceptions.add(t)
        }
        synchronized(timer) {
            this.timer = System.nanoTime() - start
        }
        list
    }

    def parseResponse(String response) {
        def words = []
        def xml = new XmlParser().parseText(response)
        xml.children().each { word ->
            words << [word:word.text(),category:word.'@p',relationship:word.'@r']
        }
        return words
    }
}
