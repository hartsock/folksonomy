package folksonomy

class UserTagController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userTagInstanceList: UserTag.list(params), userTagInstanceTotal: UserTag.count()]
    }

    def create = {
        def userTagInstance = new UserTag()
        userTagInstance.properties = params
        return [userTagInstance: userTagInstance]
    }

    def save = {
        def userTagInstance = new UserTag(params)
        if (userTagInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'userTag.label', default: 'UserTag'), userTagInstance.id])}"
            redirect(action: "show", id: userTagInstance.id)
        }
        else {
            render(view: "create", model: [userTagInstance: userTagInstance])
        }
    }

    def show = {
        def userTagInstance = UserTag.get(params.id)
        if (!userTagInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userTag.label', default: 'UserTag'), params.id])}"
            redirect(action: "list")
        }
        else {
            [userTagInstance: userTagInstance]
        }
    }

    def edit = {
        def userTagInstance = UserTag.get(params.id)
        if (!userTagInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userTag.label', default: 'UserTag'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [userTagInstance: userTagInstance]
        }
    }

    def update = {
        def userTagInstance = UserTag.get(params.id)
        if (userTagInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userTagInstance.version > version) {

                    userTagInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'userTag.label', default: 'UserTag')] as Object[], "Another user has updated this UserTag while you were editing")
                    render(view: "edit", model: [userTagInstance: userTagInstance])
                    return
                }
            }
            userTagInstance.properties = params
            if (!userTagInstance.hasErrors() && userTagInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'userTag.label', default: 'UserTag'), userTagInstance.id])}"
                redirect(action: "show", id: userTagInstance.id)
            }
            else {
                render(view: "edit", model: [userTagInstance: userTagInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userTag.label', default: 'UserTag'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def userTagInstance = UserTag.get(params.id)
        if (userTagInstance) {
            try {
                userTagInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'userTag.label', default: 'UserTag'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'userTag.label', default: 'UserTag'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userTag.label', default: 'UserTag'), params.id])}"
            redirect(action: "list")
        }
    }
}
