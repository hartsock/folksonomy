package folksonomy.parsers;

/**
 * Open Software Integrators, LLC <p/>
 * The Professional Service Delivery Experts </p>
 * shartsock@osintegrators.com</p>
 * 919 321 9855<p/>
 * <p/>
 *
 * @author Shawn Hartsock
 */
public class TaggedLink {
    private String title;
    private String href;
    private String[] tags;

    public TaggedLink(String title, String href, String[] tags) {
        this.title = title;
        this.href = href;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getHref() {
        return href;
    }

    public String[] getTags() {
        return tags;
    }
}
