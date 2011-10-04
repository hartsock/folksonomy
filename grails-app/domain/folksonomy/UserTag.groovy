package folksonomy

class UserTag {
	String username
	Tag tag
	Uri uri
    static constraints = {
		username(nullable:false)
    }

    String toString() { tag?.toString()?:'?' }
}
