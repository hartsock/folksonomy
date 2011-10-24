package folksonomy

import folksonomy.parsers.FastDeliciousHtmlParser

class DeliciousExportParserService {
    def htmlParser = new DeliciousHtmlParser()
    def fastParser = new FastDeliciousHtmlParser()
    def parsers = [
            groovy: { input -> htmlParser.parse(input) },
            fast: { input -> htmlParser.parseFast(input) },
            faster: { input -> htmlParser.parseFaster(input) },
            java: { input -> fastParser.parse(input) }
    ]

    def parserTypes() { parsers.keySet() }

    def parse(String type, InputStream inputStream) {
        assert parsers.keySet().contains(type)
        parsers[type]?.call(inputStream)
    }
}
