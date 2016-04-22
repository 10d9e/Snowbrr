package snowbrr

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_CONSUMER', 'ROLE_PROVIDER'])
class ConsumerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Consumer.list(params), model: [consumerInstanceCount: Consumer.count()]
    }

    def show(Consumer consumerInstance) {
        respond consumerInstance
    }

    def create() {
        respond new Consumer(params)
    }

    @Transactional
    def save(Consumer consumerInstance) {
        if (consumerInstance == null) {
            notFound()
            return
        }

        if (consumerInstance.hasErrors()) {
            respond consumerInstance.errors, view: 'create'
            return
        }

        consumerInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'consumer.label', default: 'Consumer'), consumerInstance.id])
                redirect consumerInstance
            }
            '*' { respond consumerInstance, [status: CREATED] }
        }
    }

    def edit(Consumer consumerInstance) {
        respond consumerInstance
    }

    @Transactional
    def update(Consumer consumerInstance) {
        if (consumerInstance == null) {
            notFound()
            return
        }

        if (consumerInstance.hasErrors()) {
            respond consumerInstance.errors, view: 'edit'
            return
        }

        consumerInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Consumer.label', default: 'Consumer'), consumerInstance.id])
                redirect consumerInstance
            }
            '*' { respond consumerInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Consumer consumerInstance) {

        if (consumerInstance == null) {
            notFound()
            return
        }

        consumerInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Consumer.label', default: 'Consumer'), consumerInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'consumer.label', default: 'Consumer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
