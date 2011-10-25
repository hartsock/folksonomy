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

    def parse = {input ->
        def links = []
        xmlSlurper.parse(input).depthFirst().grep {
            "A".equalsIgnoreCase(it.name())
        }.each { l ->
            links.add([
                    title: l.text(),
                    href: l.@href?.text(),
                    tags: l.@tags?.text()?.split(/,/) ?: [],
            ])
        }
        return links
    }

    Collection<TaggedLink> parseFast(InputStream input)  {
        Collection<TaggedLink> links = []
        def nodes = xmlSlurper.parse(input);
        def allNodes = nodes.depthFirst();
        for(NodeChild node : allNodes) {
            if("a" == node.name().toLowerCase()) {
                def title = node.text()
                def attributes = node.attributes()
                def href = attributes['href']
                def tags = attributes['tags']
                def tl = new TaggedLink(title,href,tags.split(/,/));
                links.add(tl)
            }
        }
        return links
    }

    Collection<TaggedLink> parseFaster(InputStream input)  {
        final List<TaggedLink> links = new LinkedList<TaggedLink>()
        final GPathResult nodes = xmlSlurper.parse(input);
        final Iterator iter = nodes.depthFirst();
        for(NodeChild node : iter) {
            if("a".equalsIgnoreCase(node.name())) {
                final String title = node.text();
                final Map attributes = node.attributes();
                final String href = (String) attributes.get("href");
                final String tagsString = (String) attributes.get("tags");
                final String[] tags = tagsString.split(",");
                TaggedLink tl = new TaggedLink(title,href,tags);
                links.add(tl)
            }
        }
        return links
    }

    TaggedLink tagNode(final NodeChild node) {
    }

}
