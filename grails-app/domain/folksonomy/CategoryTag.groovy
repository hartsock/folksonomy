package folksonomy

class CategoryTag {
    Category category
    Tag tag
    static constraints = {
        category(nullable: false)
        tag(nullable: false)
    }
}
