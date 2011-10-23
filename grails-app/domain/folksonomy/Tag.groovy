package folksonomy

class Tag {
    Boolean checked = false
    String name
    static constraints = {
        name(unique: true)
    }

    def getCategories() {
        CategoryTag.findByTag(this).collect({ it.category }).unique()
    }

    String toString() { name }
}
