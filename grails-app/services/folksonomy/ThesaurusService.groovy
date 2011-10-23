package folksonomy

import org.springframework.beans.factory.InitializingBean
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class ThesaurusService implements InitializingBean {
    static transactional = true
    String remoteServiceUri
    String apiKey

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

    def remoteCall = { word -> "${remoteServiceUri}/${apiKey}/${word}/xml"?.toURL()?.text }

    def wordList(word,Closure remoteLogic) {
        return parseResponse( remoteLogic.call(word.toString().replaceAll(/\s/,"%20")) )
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
