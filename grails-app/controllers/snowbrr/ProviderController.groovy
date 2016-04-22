package snowbrr

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_CONSUMER', 'ROLE_PROVIDER'])

class ProviderController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Provider.list(params), model: [providerInstanceCount: Provider.count()]
    }

    def show(Provider providerInstance) {
        respond providerInstance
    }

    def create() {
        respond new Provider(params)
    }

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

    def edit(Provider providerInstance) {
        respond providerInstance
    }

    @Transactional
    def update(Provider providerInstance) {
        if (providerInstance == null) {
            notFound()
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
