package folksonomy

import org.ccil.cowan.tagsoup.*;
/**
 * Open Software Integrators, LLC <p/>
 * The Professional Service Delivery Experts </p>
 * shartsock@osintegrators.com</p>
 * 919 321 9855<p/>
 * <p/>
 * @author Shawn Hartsock
 */
class DeliciousHtmlParser {
    def xmlSlurper = new XmlSlurper(new Parser())

    def parseHtml(InputStream inputStream) {
        def records = xmlSlurper.parse(inputStream)
        return records.depthFirst().grep { "A".equalsIgnoreCase(it.name()) }
    }

    def parse(InputStream input) {
        def dlinks = parseHtml(input)
        def links = []
        dlinks.each { l ->
            def link = [:]
            link.href = l.@href?.text()
            link.tags = l.@tags?.text()?.split(/,/)?:[]
            links.add(link)
        }
        return links
    }
}
