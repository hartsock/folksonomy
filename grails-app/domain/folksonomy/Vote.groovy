package folksonomy

class Vote {
    Uri uri
    String username
    Integer vote
    Date dateCreated
    Date lastUpdated
    static constraints = {
        username(nullable: false)
        vote(nullable: false, range: -1..1)
    }

    String toString() { vote?.toString() ?: '?' }
}
