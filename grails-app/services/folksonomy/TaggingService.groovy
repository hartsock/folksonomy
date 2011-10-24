package folksonomy

import folksonomy.dto.UserUriTags

class TaggingService {

    static transactional = false // we will manually control our transactions
     static expose = ['jmx']

    def fetchTags(tags) {

    }

    UserUriTags tagUri(String username, String uriString, List<String> tags) {
        def uri
        Uri.withTransaction { tx ->
            uri = Uri.findByUri(uriString)?:new Uri(uri:uriString)
            uri.save()
        }
        return uri?tagUri(username,uri,tags):new UserUriTags(username:username,uri:uriString,tags:tags)
    }

    UserUriTags tagUri(String username, Uri uri, List<String> tags) {

    }
}
