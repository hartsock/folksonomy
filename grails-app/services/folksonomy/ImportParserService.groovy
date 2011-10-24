package folksonomy

import folksonomy.parsers.FastDeliciousHtmlParser
/**
 * Dynamically switches between our four versions of the
 * parser for the purpose of showing performance differences
 * in demo.
 * <p/>
 * @author Shawn Hartsock
 */
class ImportParserService {
    static transactional = false
    static expose = ['jmx']

    def htmlParser = new DeliciousHtmlParser()
    def fastParser = new FastDeliciousHtmlParser()
    def parsers = [
            groovy: { input -> htmlParser.parse(input) },
            fast: { input -> htmlParser.parseFast(input) },
            faster: { input -> htmlParser.parseFaster(input) },
            java: { input -> fastParser.parse(input) }
    ]

    TaggingService taggingService

    def parserTypes() { parsers.keySet() }

    def parse(String type, InputStream inputStream) {
        assert parsers.keySet().contains(type)
        Closure closure = parsers[type]
        closure.call(inputStream)
    }

    def processImport(User user, String type, InputStream inputStream) {
        def count = 0
        parse(type,inputStream).each { bookmark ->
            taggingService.tagUriString(user,bookmark.title,bookmark.href,bookmark.tags?.toList())
            count++
        }
        return count
    }
}
