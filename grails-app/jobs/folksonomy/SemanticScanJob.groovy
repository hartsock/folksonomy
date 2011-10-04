package folksonomy

class SemanticScanJob {
    def timeout = new BigInteger( (24*60*60*1000)/10000 ) // execute job at most 10,000 times a day
    def semanticService

    def execute() {
        semanticService.scan()
    }
}
