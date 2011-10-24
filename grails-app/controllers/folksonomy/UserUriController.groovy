package folksonomy

import org.springframework.dao.DataIntegrityViolationException

class UserUriController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userUriInstanceList: UserUri.list(params), userUriInstanceTotal: UserUri.count()]
    }

    def create() {
        [userUriInstance: new UserUri(params)]
    }

    def save() {
        def userUriInstance = new UserUri(params)
        if (!userUriInstance.save(flush: true)) {
            render(view: "create", model: [userUriInstance: userUriInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'userUri.label', default: 'UserUri'), userUriInstance.id])
        redirect(action: "show", id: userUriInstance.id)
    }

    def show() {
        def userUriInstance = UserUri.get(params.id)
        if (!userUriInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userUri.label', default: 'UserUri'), params.id])
            redirect(action: "list")
            return
        }

        [userUriInstance: userUriInstance]
    }

    def edit() {
        def userUriInstance = UserUri.get(params.id)
        if (!userUriInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userUri.label', default: 'UserUri'), params.id])
            redirect(action: "list")
            return
        }

        [userUriInstance: userUriInstance]
    }

    def update() {
        def userUriInstance = UserUri.get(params.id)
        if (!userUriInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userUri.label', default: 'UserUri'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (userUriInstance.version > version) {
                userUriInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'userUri.label', default: 'UserUri')] as Object[],
                        "Another user has updated this UserUri while you were editing")
                render(view: "edit", model: [userUriInstance: userUriInstance])
                return
            }
        }

        userUriInstance.properties = params

        if (!userUriInstance.save(flush: true)) {
            render(view: "edit", model: [userUriInstance: userUriInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'userUri.label', default: 'UserUri'), userUriInstance.id])
        redirect(action: "show", id: userUriInstance.id)
    }

    def delete() {
        def userUriInstance = UserUri.get(params.id)
        if (!userUriInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userUri.label', default: 'UserUri'), params.id])
            redirect(action: "list")
            return
        }

        try {
            userUriInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'userUri.label', default: 'UserUri'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userUri.label', default: 'UserUri'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
