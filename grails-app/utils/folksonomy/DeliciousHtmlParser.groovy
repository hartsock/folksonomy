package folksonomy

import org.ccil.cowan.tagsoup.*
import folksonomy.parsers.TaggedLink
import groovy.util.slurpersupport.NodeChild
import groovy.util.slurpersupport.GPathResult;
/**
 * Open Software Integrators, LLC <p/>
 * The Professional Service Delivery Experts </p>
 * shartsock@osintegrators.com</p>
 * 919 321 9855<p/>
 * <p/>
 * This code is deliberately "WET" to allow for apples-to apples
 * benchmarking with FastDeliciousHtmlParser
 * <p/>
 * @author Shawn Hartsock
 */
class DeliciousHtmlParser {
    def xmlSlurper = new XmlSlurper(new Parser())

    def parse(InputStream input) {
        def records = xmlSlurper.parse(input)
        def dlinks = records.depthFirst().grep { "A".equalsIgnoreCase(it.name()) }
        def links = []
        dlinks.each { l ->
            def link = [:]
            link.title = l.text()
            link.href = l.@href?.text()
            link.tags = l.@tags?.text()?.split(/,/)?:[]
            links.add(link)
        }
        return links
    }

    List<TaggedLink> parseFaster(InputStream input)  {
        final List<TaggedLink> links = new LinkedList<TaggedLink>()
        final GPathResult nodes = xmlSlurper.parse(input);
        final Iterator iter = nodes.depthFirst();
        for(NodeChild node : iter) {
            if("a" == node.name().toLowerCase()) {
                String title = node.text()
                Map attributes = node.attributes()
                final String href = attributes['href']
                final String tags = attributes['tags']
                TaggedLink tl = new TaggedLink(title,href,tags.split(/,/));
                links.add(tl)
            }
        }
        return links
    }

    List<TaggedLink> parseFastest(InputStream input)  {
        final List<TaggedLink> links = new LinkedList<TaggedLink>()
        final GPathResult nodes = xmlSlurper.parse(input);
        final Iterator iter = nodes.depthFirst();
        for(NodeChild node : iter) {
            if("a".equalsIgnoreCase(node.name())) {
                TaggedLink tl = tagNode(node);
                links.add(tl)
            }
        }
        return links
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
