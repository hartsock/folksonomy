package folksonomy

class SemanticScanJob {
    def timeout = 8700 //(24*60*60*1000)/10000 // execute job at most 10,000 times a day
    // NOTE: the SLA on the folksonomy.ThesaurusService remote connection stipulates
    // we may use their service at most 10,000 times in a day. This is roughly once
    // every 8.7 seconds. So we are throttled to that rate.

    SemanticService semanticService

    def execute() {
        Tag tag = Tag.findByChecked(false)
        if (tag) {
            semanticService.categorize(tag)
            tag?.discard() // throw away so we can lock it later
            Tag.withTransaction { status ->
                tag = Tag.lock(tag.id)
                tag.setChecked(true)
                tag.save(flush: true)
                status.flush()
            }
        }
    }
}
