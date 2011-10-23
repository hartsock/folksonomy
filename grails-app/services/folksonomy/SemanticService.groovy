package folksonomy

import grails.orm.HibernateCriteriaBuilder

class SemanticService {
    static transactional = true
    def sessionFactory
    def thesaurusService

    void scan() {
        def tag = Tag.findByChecked(false)
        if(tag) {
            categorize(tag)
            tag.setChecked(true)
            if(!tag.save()) { throw new IllegalStateException("${tag.errors}") }
        }
    }

    void categorize(Tag tag) {
        def words = thesaurusService.wordList(tag)
        categorize(tag,words)
    }

    void categorize(Tag tag, words) {
        words.each {
            associate(it.category, it.word)
            relate(tag,it.word,it.relationship)
        }
    }

    void relate(Tag root, String tagName, String relationship) {
        def tag = lookupTag(tagName)
        switch(relationship) {
            case 'antonym':
                new Antonym(root:root,tag:tag).save()
                break
            case 'synonym':
                new Synonym(root:root,tag:tag).save()
                break
        }
    }

    void associate(String categoryName, String tagName) {
        def tag = lookupTag(tagName)
        def category = lookupCategory(categoryName)
        def association = new CategoryTag(category:category,tag:tag)
        if ( !association.save() ) {
            throw new IllegalStateException("${association.errors}")
        }
    }

    Tag lookupTag(word) {
        lookupDomain(word,Tag)
    }

    Category lookupCategory(word) {
        lookupDomain(word,Category)
    }

    def lookupDomain(word, clazz) {
        def obj = searchForDomain(clazz,word)
        if( !obj ) {
            obj = clazz.newInstance(name:word)
            if(!obj.save()) {
                throw new IllegalStateException("${obj.errors}")
            }
        }
        return obj
    }

    def searchForDomain = { clazz,word ->
        new HibernateCriteriaBuilder(clazz, sessionFactory).get {
            eq("name", word)
        }
    }
}
