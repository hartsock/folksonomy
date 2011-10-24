import folksonomy.Tag
import folksonomy.Authority
import folksonomy.User
import grails.plugins.springsecurity.SpringSecurityService
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import folksonomy.UserAuthority

class BootStrap {
    SpringSecurityService springSecurityService
    def init = { servletContext ->
        // fix bug in spring security ui: no securityConfig
        SpringSecurityUtils.securityConfig =  ConfigurationHolder.config.grails.plugins.springsecurity
        if(Tag.count() == 0) {
            new Tag(name:'java').save()
        }
        if(User.count() == 0) {
            def admin = new User(username:'admin',password:'admin',enabled:true)
            admin.save()
            def roleUser = new Authority(authority:'ROLE_USER')
            roleUser.save()
            def roleAdmin = new Authority(authority:'ROLE_ADMIN')
            roleAdmin.save()
            new UserAuthority(user:admin,authority: roleUser).save()
            new UserAuthority(user:admin,authority: roleAdmin).save()
        }
    }
    def destroy = {
    }
}
