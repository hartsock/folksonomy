package folksonomy;

import grails.test.GrailsUnitTestCase;

/**
 * Open Software Integrators, LLC <p/>
 * The Professional Service Delivery Experts </p>
 * shartsock@osintegrators.com</p>
 * 919 321 9855<p/>
 * <p/>
 *
 * @author Shawn Hartsock
 */
public class ThesaurusServiceTest extends GrailsUnitTestCase {
    def mockResponseString = '<?xml version="1.0"?><words><w p="noun" r="syn">passion</w><w p="noun" r="syn">beloved</w><w p="noun" r="syn">dear</w><w p="noun" r="syn">dearest</w><w p="noun" r="syn">honey</w><w p="noun" r="syn">sexual love</w><w p="noun" r="syn">erotic love</w><w p="noun" r="syn">lovemaking</w><w p="noun" r="syn">making love</w><w p="noun" r="syn">love life</w><w p="noun" r="syn">concupiscence</w><w p="noun" r="syn">emotion</w><w p="noun" r="syn">eros</w><w p="noun" r="syn">loved one</w><w p="noun" r="syn">lover</w><w p="noun" r="syn">object</w><w p="noun" r="syn">physical attraction</w><w p="noun" r="syn">score</w><w p="noun" r="syn">sex</w><w p="noun" r="syn">sex activity</w><w p="noun" r="syn">sexual activity</w><w p="noun" r="syn">sexual desire</w><w p="noun" r="syn">sexual practice</w><w p="noun" r="ant">hate</w><w p="noun" r="usr">amour</w><w p="verb" r="syn">love</w><w p="verb" r="syn">enjoy</w><w p="verb" r="syn">roll in the hay</w><w p="verb" r="syn">make out</w><w p="verb" r="syn">make love</w><w p="verb" r="syn">sleep with</w><w p="verb" r="syn">get laid</w><w p="verb" r="syn">have sex</w><w p="verb" r="syn">know</w><w p="verb" r="syn">do it</w><w p="verb" r="syn">be intimate</w><w p="verb" r="syn">have intercourse</w><w p="verb" r="syn">have it away</w><w p="verb" r="syn">have it off</w><w p="verb" r="syn">screw</w><w p="verb" r="syn">jazz</w><w p="verb" r="syn">eff</w><w p="verb" r="syn">hump</w><w p="verb" r="syn">lie with</w><w p="verb" r="syn">bed</w><w p="verb" r="syn">have a go at it</w><w p="verb" r="syn">bang</w><w p="verb" r="syn">get it on</w><w p="verb" r="syn">bonk</w><w p="verb" r="syn">copulate</w><w p="verb" r="syn">couple</w><w p="verb" r="syn">like</w><w p="verb" r="syn">mate</w><w p="verb" r="syn">pair</w><w p="verb" r="ant">hate</w></words>'

    def thesaurusService

    void testParseRemoteResponse() {
        def words = thesaurusService.parseResponse(mockResponseString)
        assert words.size() == 55
        def nouns = words.grep { it.category == 'noun' }
        assert nouns.size() == 25
        def verbs = words.grep { it.category == 'verb' }
        assert verbs.size() == 30
    }

    void testRemoteCall() {
        def responseText = thesaurusService.remoteCall('love')
        def words = thesaurusService.parseResponse(responseText)
        def localWords = thesaurusService.parseResponse(mockResponseString)
        assert words.size() == 55
        def nouns = words.grep { it.category == 'noun' }
        assert nouns.size() == 25
        def verbs = words.grep { it.category == 'verb' }
        assert verbs.size() == 30
        assert localWords == words
    }

}
