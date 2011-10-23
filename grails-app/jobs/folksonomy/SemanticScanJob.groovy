package folksonomy

class SemanticScanJob {
    def timeout =  (24*60*60*1000)/10000 // execute job at most 10,000 times a day
    SemanticService semanticService

    def execute() {
        Tag.withTransaction { status ->
            def tag = Tag.findByChecked(false)
            tag.discard() // throw away so we can lock it later
            tag = Tag.lock(tag.id)
            if(tag) {
                semanticService.categorize(tag)
                tag.setChecked(true)
                if(!tag.save()) {
                    throw new IllegalStateException("${tag.errors}")
                }
                status.flush()
            }
        }
    }
}
