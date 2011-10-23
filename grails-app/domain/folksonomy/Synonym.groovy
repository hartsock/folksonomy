package folksonomy

class Synonym {
    Tag root
    Tag tag
    static constraints = {
        root(nullable: false)
        tag(nullable: false, validator: { val, obj -> "${val}" != "${obj.root}" })
    }

    String toString() { tag.toString() }
}
