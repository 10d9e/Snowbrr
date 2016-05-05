package snowbrr

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_CONSUMER', 'ROLE_PROVIDER'])
class ProviderController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def providerService

    def springSecurityService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Provider.list(params), model: [providerInstanceCount: Provider.count()]
    }

    def show(Provider providerInstance) {
        Consumer consumer = Consumer.findByUser( springSecurityService.currentUser )
        respond providerInstance, model:[consumerId: consumer?.id]
    }

    @Secured(['ROLE_PROVIDER', 'ROLE_ADMIN'])
    def create() {
        respond new Provider(params)
    }

    def list() {
        render providerService.listProviders() as JSON
    }

    @Secured(['ROLE_PROVIDER', 'ROLE_ADMIN'])
    @Transactional
    def save(Provider providerInstance) {
        if (providerInstance == null) {
            notFound()
            return
        }

        if (providerInstance.hasErrors()) {
            respond providerInstance.errors, view: 'create'
            return
        }

        providerInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'provider.label', default: 'Provider'), providerInstance.id])
                redirect providerInstance
            }
            '*' { respond providerInstance, [status: CREATED] }
        }
    }

    @Secured(['ROLE_PROVIDER', 'ROLE_ADMIN'])
    def edit(Provider providerInstance) {
        if(providerInstance?.user != springSecurityService.currentUser){
            flash.message = 'You do not have access to update this provider.'
            respond providerInstance, view: 'show'
            return
        }
        respond providerInstance
    }

    @Secured(['ROLE_PROVIDER', 'ROLE_ADMIN'])
    @Transactional
    def update(Provider providerInstance) {
        if (providerInstance == null) {
            notFound()
            return
        }

        if(providerInstance.user != springSecurityService.currentUser){
            flash.message = 'You do not have access to update this provider.'
            return
        }

        if (providerInstance.hasErrors()) {
            respond providerInstance.errors, view: 'edit'
            return
        }

        providerInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Provider.label', default: 'Provider'), providerInstance.id])
                redirect providerInstance
            }
            '*' { respond providerInstance, [status: OK] }
        }
    }

    @Secured('ROLE_ADMIN')
    @Transactional
    def delete(Provider providerInstance) {

        if (providerInstance == null) {
            notFound()
            return
        }

        providerInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Provider.label', default: 'Provider'), providerInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'provider.label', default: 'Provider'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
