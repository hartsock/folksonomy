package folksonomy

import org.springframework.web.multipart.MultipartFile
import java.security.Principal
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser

class FolksonomyController {

    static allowedMethods = [process: "POST"]

    def springSecurityService
    def importParserService

    def index = {
        redirect(action:'list')
    }

    @Secured(['ROLE_USER'])
    def list = {
        if(! springSecurityService.isLoggedIn()) {
            redirect(controller:'login')
        }
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        GrailsUser principal = springSecurityService.principal
        def username = principal.username
        def user = User.findByUsername(username)
        [userUriInstanceList: UserUri.findAllByUser(user,params), userUriInstanceTotal: UserUri.countByUser(user)]
    }

    @Secured(['ROLE_USER'])
    def upload = {
        GrailsUser principal = springSecurityService.principal
        def username = principal.username
        def user = User.findByUsername(username)
        [user:user,types:['groovy','fast','faster','java']]
    }

    @Secured(['ROLE_USER'])
    def process = {
        GrailsUser principal = springSecurityService.principal
        def username = principal.username
        def user = username?User.findByUsername(username):null
        assert user != null
        String type = params.type?:'groovy'
        MultipartFile f = request.getFile('export')
        if(!f.empty) {
            def count = importParserService.processImport(user, type, f.inputStream)
            flash.message = "processed ${count} bookmarks"
            redirect(view:'list')
        }
        else {
            flash.message = "please specify a file"
            redirect(view:'upload')
        }
    }
}
