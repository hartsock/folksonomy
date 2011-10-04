package folksonomy

import grails.test.*

class CategoryTagTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testMapping() {
        def tag = new Tag(name:'java')
        def category = new Category(name:'technology')
        def categoryTag = new CategoryTag(tag:tag,category:category)
        assert categoryTag.save()
        assert categoryTag.tag.name == 'java'
        assert categoryTag.toString() == tag.toString()
    }
}
