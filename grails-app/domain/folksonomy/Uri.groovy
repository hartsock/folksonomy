package folksonomy

class Uri {
    String uri
    static constraints = {
        uri(url: true, nullable: false, unique: true)
    }

    String toString() { uri }
}
