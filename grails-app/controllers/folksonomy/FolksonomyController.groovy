package folksonomy

import org.springframework.web.multipart.MultipartFile
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser

class FolksonomyController {

    static allowedMethods = [process: "POST"]

    def springSecurityService
    def importParserService
    def profilerLog

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
        profilerLog.startProfiling("processingUpload")
        def startTime = System.nanoTime();
        GrailsUser principal = springSecurityService.principal
        def username = principal.username
        def user = username?User.findByUsername(username):null
        assert user != null
        String type = params.type?:'groovy'
        MultipartFile f = request.getFile('export')
        String view = 'list'
        if(!f.empty) {
            def count = importParserService.processImport(user, type, f.inputStream)
            flash.message = "processed ${count} bookmarks"
        }
        else {
            flash.message = "please specify a file"
            view = 'upload'
        }
        def endTime = System.nanoTime();
        profilerLog.stopProfiling()
        log.info("processing time: ${endTime - startTime}")
        render(view:'list',model:[userUriInstanceList: UserUri.findAllByUser(user,params), userUriInstanceTotal: UserUri.countByUser(user)])
    }

    @Secured(['ROLE_USER'])
    def tag = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        GrailsUser principal = springSecurityService.principal
        def username = principal.username
        def user = username?User.findByUsername(username):null
        assert user != null
        def list = UserUri.findAll(
                "from UserUri as userUri join userUri.tags as tags where :id in tags ",
                [id:params.id],
                params)

        [userUriInstanceList: list, userUriInstanceTotal: UserUri.countByUser(user)]
    }
}
