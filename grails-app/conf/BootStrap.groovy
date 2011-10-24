import folksonomy.Tag
import folksonomy.Authority
import folksonomy.User
import grails.plugins.springsecurity.SpringSecurityService
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class BootStrap {
    SpringSecurityService springSecurityService
    def init = { servletContext ->
        // fix bug in spring security ui: no securityConfig
        SpringSecurityUtils.securityConfig =  ConfigurationHolder.config.grails.plugins.springsecurity
        if(Tag.count() == 0) {
            new Tag(name:'java').save()
        }
        if(Authority.count() == 0) {
            new Authority(authority:'ROLE_USER').save()
            new Authority(authority:'ROLE_ADMIN').save()
        }
        if(User.count() == 0) {
            new User(username:'admin',password:'admin',enabled:true).save()
        }
    }
    def destroy = {
    }
}
