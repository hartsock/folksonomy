package folksonomy

class SemanticScanJob {
    def timeout =  (24*60*60*1000)/10000 // execute job at most 10,000 times a day
    // NOTE: the SLA on the folksonomy.ThesaurusService remote connection stipulates
    // we may use their service at most 10,000 times in a day. This is roughly once
    // every 8.7 seconds. So we are throttled to that rate.
    SemanticService semanticService

    def execute() {
        Tag.withTransaction { status ->
            Tag tag = Tag.findByChecked(false)
            tag?.discard() // throw away so we can lock it later
            if(tag) {
                tag = Tag.lock(tag.id)
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
