package folksonomy.parsers;

import groovy.util.XmlSlurper;
import groovy.util.slurpersupport.GPathResult;
import groovy.util.slurpersupport.NodeChild;
import org.ccil.cowan.tagsoup.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Open Software Integrators, LLC <p/>
 * The Professional Service Delivery Experts </p>
 * shartsock@osintegrators.com</p>
 * 919 321 9855<p/>
 * <p/>
 * Uses groovy API in a POJO, just to show that Java can benefit from Groovy API
 * even if you don't use the Groovy programming language.
 * <p/>
 * @author Shawn Hartsock
 */
public class FastDeliciousHtmlParser {
    private final XmlSlurper parser = new XmlSlurper(new Parser());

    public List<TaggedLink> parse(final InputStream inputStream) throws IOException, SAXException {
        final List<TaggedLink> taggedLinks = new LinkedList<TaggedLink>();
        final GPathResult nodes = parser.parse(inputStream);
        final Iterator iter = nodes.depthFirst();
        while(iter.hasNext()) {
            NodeChild child = (NodeChild) iter.next();
            if("a".equalsIgnoreCase(child.name())) {
                TaggedLink taggedLink = tagNode(child);
                taggedLinks.add(taggedLink);
            }
        }
        return taggedLinks;
    }

    TaggedLink tagNode(final NodeChild node) {
        final String title = node.text();
        Map attributes = node.attributes();
        final String href = (String) attributes.get("href");
        final String tagsString = (String) attributes.get("tags");
        final String[] tags = tagsString.split(",");
        return new TaggedLink(title,href,tags);
    }
}
