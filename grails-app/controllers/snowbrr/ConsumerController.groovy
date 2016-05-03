package snowbrr

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_CONSUMER', 'ROLE_PROVIDER'])
class ConsumerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def springSecurityService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Consumer.list(params), model: [consumerInstanceCount: Consumer.count()]
    }

    def map(Integer max) {
        index(max)
    }

    def show(Consumer consumerInstance) {
        Provider provider = Provider.findByUser( springSecurityService.currentUser )
        respond consumerInstance, model:[providerId: provider?.id]
    }

    @Secured(['ROLE_CONSUMER', 'ROLE_ADMIN'])
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

    @Secured(['ROLE_CONSUMER', 'ROLE_ADMIN'])
    def edit(Consumer consumerInstance) {
        if(consumerInstance.user != springSecurityService.currentUser){
            flash.message = 'You do not have access to update this customer.'
            respond consumerInstance, view: 'show'
            return
        }
        respond consumerInstance
    }

    @Secured(['ROLE_CONSUMER', 'ROLE_ADMIN'])
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

    @Secured('ROLE_ADMIN')
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
