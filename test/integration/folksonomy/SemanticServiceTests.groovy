package folksonomy

import grails.test.*

class SemanticServiceTests extends GrailsUnitTestCase {
    def semanticService

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testPolymorphicHelper() {
        assert semanticService.sessionFactory != null
        Tag tag = semanticService.lookupDomain("foo",Tag)
        assert tag.name == "foo"
        Tag tag2 = semanticService.lookupDomain("foo",Tag)
        assert tag2.name == "foo"
        assert tag.id == tag2.id
        Category category = semanticService.lookupDomain("noun",Category)
        assert category.name == "noun"
    }


}
