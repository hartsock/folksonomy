package folksonomy.dto

/**
 * Open Software Integrators, LLC <p/>
 * The Professional Service Delivery Experts </p>
 * shartsock@osintegrators.com</p>
 * 919 321 9855<p/>
 * <p/>
 * @author Shawn Hartsock
 */
class UserUriTagsDTO {
    String username
    String title
    String description
    URI uri
    Set<String> tags = []
    void addTag(tag) {
        tags.add(tag.toString())
    }
}
