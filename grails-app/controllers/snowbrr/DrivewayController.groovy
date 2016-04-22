package snowbrr

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_CONSUMER', 'ROLE_PROVIDER'])

class DrivewayController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Driveway.list(params), model: [drivewayInstanceCount: Driveway.count()]
    }

    def show(Driveway drivewayInstance) {
        respond drivewayInstance
    }

    def create() {
        respond new Driveway(params)
    }

    @Transactional
    def save(Driveway drivewayInstance) {
        if (drivewayInstance == null) {
            notFound()
            return
        }

        if (drivewayInstance.hasErrors()) {
            respond drivewayInstance.errors, view: 'create'
            return
        }

        drivewayInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'driveway.label', default: 'Driveway'), drivewayInstance.id])
                redirect drivewayInstance
            }
            '*' { respond drivewayInstance, [status: CREATED] }
        }
    }

    def edit(Driveway drivewayInstance) {
        respond drivewayInstance
    }

    @Transactional
    def update(Driveway drivewayInstance) {
        if (drivewayInstance == null) {
            notFound()
            return
        }

        if (drivewayInstance.hasErrors()) {
            respond drivewayInstance.errors, view: 'edit'
            return
        }

        drivewayInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Driveway.label', default: 'Driveway'), drivewayInstance.id])
                redirect drivewayInstance
            }
            '*' { respond drivewayInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Driveway drivewayInstance) {

        if (drivewayInstance == null) {
            notFound()
            return
        }

        drivewayInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Driveway.label', default: 'Driveway'), drivewayInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'driveway.label', default: 'Driveway'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
