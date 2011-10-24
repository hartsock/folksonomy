package folksonomy

class UserUri {
    String title
    String description

    User user
    Uri uri
    static hasMany = [tags:Tag]

    static constraints = {
        title(nullable:false)
        description(nullable:true)
    }

    static mapping = {
        tags lazy:false
    }
}
