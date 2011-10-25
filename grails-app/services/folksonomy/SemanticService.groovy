package folksonomy

import grails.orm.HibernateCriteriaBuilder

class SemanticService {
    static transactional = true
    static expose = ['jmx']

    def sessionFactory
    def thesaurusService

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
                new Antonym(root:root,tag:tag).save(flush:true)
                break
            case 'synonym':
                new Synonym(root:root,tag:tag).save(flush:true)
                break
        }
    }

    void associate(String categoryName, String tagName) {
        def tag = lookupTag(tagName)
        def category = lookupCategory(categoryName)
        def association = new CategoryTag(category:category,tag:tag)
        if ( !association.save(flush:true) ) {
            throw new IllegalStateException("${association.errors}")
        }
    }

    Tag lookupTag(word) {
        Tag tag = Tag.findByName(word)
        if(!tag) {
            tag = new Tag(name:word)
            tag.save(flush:true)
        }
        // tag.discard()
        // tag = Tag.lock(tag.id)
        return tag
    }

    Category lookupCategory(word) {
        lookupDomain(word,Category)
    }

    def lookupDomain(word, clazz) {
        def obj = searchForDomain(clazz,word)
        if( !obj ) {
            Tag.withTransaction {
                obj = clazz.newInstance(name:word)
                obj.save(flush:true)
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
