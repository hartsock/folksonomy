package folksonomy;

/**
 * Open Software Integrators, LLC <p/>
 * The Professional Service Delivery Experts </p>
 * shartsock@osintegrators.com</p>
 * 919 321 9855<p/>
 * <p/>
 *
 * @author Shawn Hartsock
 */
public class DeliciousHtmlParserTest extends GroovyTestCase {
    DeliciousHtmlParser deliciousHtmlParser

    protected void setUp() {
        super.setUp()
        deliciousHtmlParser = new DeliciousHtmlParser()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testParserOnExport() {
        def sample = getClass().getResourceAsStream("delicious.html")
        assert sample != null
        def links = deliciousHtmlParser.parse(sample)
        println links
        assert links
        assert links.size() > 0
        assert links.size() == 457
    }
}
