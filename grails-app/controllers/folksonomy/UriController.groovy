package folksonomy

import org.springframework.dao.DataIntegrityViolationException

class UriController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [uriInstanceList: Uri.list(params), uriInstanceTotal: Uri.count()]
    }

    def create() {
        [uriInstance: new Uri(params)]
    }

    def save() {
        def uriInstance = new Uri(params)
        if (!uriInstance.save(flush: true)) {
            render(view: "create", model: [uriInstance: uriInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'uri.label', default: 'Uri'), uriInstance.id])
        redirect(action: "show", id: uriInstance.id)
    }

    def show() {
        def uriInstance = Uri.get(params.id)
        if (!uriInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'uri.label', default: 'Uri'), params.id])
            redirect(action: "list")
            return
        }

        [uriInstance: uriInstance]
    }

    def edit() {
        def uriInstance = Uri.get(params.id)
        if (!uriInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'uri.label', default: 'Uri'), params.id])
            redirect(action: "list")
            return
        }

        [uriInstance: uriInstance]
    }

    def update() {
        def uriInstance = Uri.get(params.id)
        if (!uriInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'uri.label', default: 'Uri'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (uriInstance.version > version) {
                uriInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'uri.label', default: 'Uri')] as Object[],
                        "Another user has updated this Uri while you were editing")
                render(view: "edit", model: [uriInstance: uriInstance])
                return
            }
        }

        uriInstance.properties = params

        if (!uriInstance.save(flush: true)) {
            render(view: "edit", model: [uriInstance: uriInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'uri.label', default: 'Uri'), uriInstance.id])
        redirect(action: "show", id: uriInstance.id)
    }

    def delete() {
        def uriInstance = Uri.get(params.id)
        if (!uriInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'uri.label', default: 'Uri'), params.id])
            redirect(action: "list")
            return
        }

        try {
            uriInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'uri.label', default: 'Uri'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'uri.label', default: 'Uri'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
