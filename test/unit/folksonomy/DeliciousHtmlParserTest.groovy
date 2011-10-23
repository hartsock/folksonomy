package folksonomy

import folksonomy.parsers.FastDeliciousHtmlParser
import java.text.NumberFormat;

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
    def formatter = NumberFormat.getInstance()
    DeliciousHtmlParser deliciousHtmlParser

    protected void setUp() {
        super.setUp()
        deliciousHtmlParser = new DeliciousHtmlParser()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testParserSpeedNormal() {
        def parser = new DeliciousHtmlParser()
        def sample = getClass().getResourceAsStream("delicious.html")
        assert sample != null
        def endTime;
        def startTime = System.nanoTime();
        def links = parser.parse(sample)
        endTime = System.nanoTime()
        println "Elapsed time = " + formatter.format(endTime - startTime)
        assert links
        assert links.size() > 0
        assert links.size() == 457
    }

    void testParserSpeedFast() {
        def parser = new DeliciousHtmlParser()
        def sample = getClass().getResourceAsStream("delicious.html")
        assert sample != null
        def endTime;
        def startTime = System.nanoTime();
        def links = parser.parseFast(sample)
        endTime = System.nanoTime()
        println "Elapsed time = " + formatter.format(endTime - startTime)
        assert links
        assert links.size() > 0
        assert links.size() == 457
    }

    void testParserSpeedJava() {
        def parser = new FastDeliciousHtmlParser()
        def sample = getClass().getResourceAsStream("delicious.html")
        assert sample != null
        def endTime;
        def startTime = System.nanoTime();
        def links = parser.parse(sample)
        endTime = System.nanoTime()
        println "Elapsed time = " + formatter.format(endTime - startTime)
        assert links
        assert links.size() > 0
        assert links.size() == 457
    }
}
