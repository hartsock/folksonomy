package folksonomy

import org.springframework.dao.DataIntegrityViolationException

class UserUriTagController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userUriTagInstanceList: UserUriTag.list(params), userUriTagInstanceTotal: UserUriTag.count()]
    }

    def create() {
        [userUriTagInstance: new UserUriTag(params)]
    }

    def save() {
        def userUriTagInstance = new UserUriTag(params)
        if (!userUriTagInstance.save(flush: true)) {
            render(view: "create", model: [userUriTagInstance: userUriTagInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'userUriTag.label', default: 'UserUriTag'), userUriTagInstance.id])
        redirect(action: "show", id: userUriTagInstance.id)
    }

    def show() {
        def userUriTagInstance = UserUriTag.get(params.id)
        if (!userUriTagInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userUriTag.label', default: 'UserUriTag'), params.id])
            redirect(action: "list")
            return
        }

        [userUriTagInstance: userUriTagInstance]
    }

    def edit() {
        def userUriTagInstance = UserUriTag.get(params.id)
        if (!userUriTagInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userUriTag.label', default: 'UserUriTag'), params.id])
            redirect(action: "list")
            return
        }

        [userUriTagInstance: userUriTagInstance]
    }

    def update() {
        def userUriTagInstance = UserUriTag.get(params.id)
        if (!userUriTagInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userUriTag.label', default: 'UserUriTag'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (userUriTagInstance.version > version) {
                userUriTagInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'userUriTag.label', default: 'UserUriTag')] as Object[],
                        "Another user has updated this UserUriTag while you were editing")
                render(view: "edit", model: [userUriTagInstance: userUriTagInstance])
                return
            }
        }

        userUriTagInstance.properties = params

        if (!userUriTagInstance.save(flush: true)) {
            render(view: "edit", model: [userUriTagInstance: userUriTagInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'userUriTag.label', default: 'UserUriTag'), userUriTagInstance.id])
        redirect(action: "show", id: userUriTagInstance.id)
    }

    def delete() {
        def userUriTagInstance = UserUriTag.get(params.id)
        if (!userUriTagInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userUriTag.label', default: 'UserUriTag'), params.id])
            redirect(action: "list")
            return
        }

        try {
            userUriTagInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'userUriTag.label', default: 'UserUriTag'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userUriTag.label', default: 'UserUriTag'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
