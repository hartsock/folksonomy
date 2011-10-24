package folksonomy

import folksonomy.dto.UserUriTagsDTO

class TaggingService {

    static transactional = false // we will manually control our transactions
    static expose = ['jmx']

    def fetchTags(User user, Uri uri) {
        UserUri.findByUserAndUri(user,uri).collect { UserUri userUriTag -> userUriTag.uriTag.tag }
    }

    Collection<Tag> tags(List<String> tags) {
        Collection<Tag> list = []
        tags.each { String name ->
            list.add(tag(name))
        }
        return list
    }

    Tag tag(String name) {
        def t = Tag.findByName(name) ?: new Tag(name: name)
        t.save(flush:true)
        return t
    }

    Uri uri(String uriString) {
        def u = Uri.findByUri(uriString) ?: new Uri(uri: uriString)
        u.save(flush:true)
        return Uri.get(u.id)
    }

    UserUriTagsDTO tagUri(User user, String uriString, List<String> tags) {
        def uri = uri(uriString)
        return uri ? tagUri(user, uri, tags) : new UserUriTagsDTO(user: user, uri: uri, tags: tags)
    }

    UserUriTagsDTO tagUri(User user, String title, String href, Collection tags) {
        Uri uri = uri(href)
        UserUriTagsDTO userUriTagsDTO = tagUri(user,title,uri,tags)
        userUriTagsDTO.title = title
        return userUriTagsDTO
    }

    UserUriTagsDTO tagUri(User user, String title, Uri uri, Collection tags) {
        UserUriTagsDTO userUriTagsDTO = new UserUriTagsDTO()
        userUriTagsDTO.with {
            setTitle(title)
            setUsername(user.username)
            setUri(uri.toURI())
        }
        UserUri userUri = UserUri.findByUserAndUri(user,uri)?:new UserUri(
                    user:user,
                    uri:uri
            )
        userUri.setTitle(title)
        if(userUri.save(flush:true)) {
            this.tags(tags).each { Tag tag ->
                if(tag.validate()) {
                    userUri.discard()
                    userUri = UserUri.lock(userUri.id)
                    userUri.addToTags(tag)
                    if(userUri.save(flush:true)) {
                        userUriTagsDTO.addTag(tag.name)
                    }
                }
            }
        }
        else {
            log.error userUri.errors
        }
        return userUriTagsDTO
    }

    UserUriTagsDTO tagUriString(User user, String title, String href, Collection tags) {
        tagUri(user,title,uri(href),tags)
    }
}
