package folksonomy
/**
 * in example: noun,
 */
class Category {
    String name
    static constraints = {
        name(unique: true)
    }

    def getTags(def params = [offset: 0, size: 10]) {
        CategoryTag.findAllByCategory(this, params).collect { it.tag }
    }

    def addToTags(Tag tag) {
        new CategoryTag(category: this, tag: tag).save()
    }

    String toString() { name }
}
