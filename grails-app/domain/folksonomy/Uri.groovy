package folksonomy

class Uri {
    String uri
    static constraints = {
        uri(url: true, nullable: false, unique: true)
    }

    String toString() { uri }
    URI toURI() { uri.toURI() }
    def asType(Class clazz) {
        switch(clazz) {
            case URI:
                return this.toURI()
                break
            case String:
                return this.toString()
                break
        }
    }
}
