package snowbrr

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_CONSUMER', 'ROLE_PROVIDER'])

class ProviderReviewController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProviderReview.list(params), model: [providerReviewInstanceCount: ProviderReview.count()]
    }

    def show(ProviderReview providerReviewInstance) {
        respond providerReviewInstance
    }

    def create() {
        respond new ProviderReview(params)
    }

    @Transactional
    def save(ProviderReview providerReviewInstance) {
        if (providerReviewInstance == null) {
            notFound()
            return
        }

        if (providerReviewInstance.hasErrors()) {
            respond providerReviewInstance.errors, view: 'create'
            return
        }

        providerReviewInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'providerReview.label', default: 'ProviderReview'), providerReviewInstance.id])
                redirect providerReviewInstance
            }
            '*' { respond providerReviewInstance, [status: CREATED] }
        }
    }

    def edit(ProviderReview providerReviewInstance) {
        respond providerReviewInstance
    }

    @Transactional
    def update(ProviderReview providerReviewInstance) {
        if (providerReviewInstance == null) {
            notFound()
            return
        }

        if (providerReviewInstance.hasErrors()) {
            respond providerReviewInstance.errors, view: 'edit'
            return
        }

        providerReviewInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ProviderReview.label', default: 'ProviderReview'), providerReviewInstance.id])
                redirect providerReviewInstance
            }
            '*' { respond providerReviewInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ProviderReview providerReviewInstance) {

        if (providerReviewInstance == null) {
            notFound()
            return
        }

        providerReviewInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ProviderReview.label', default: 'ProviderReview'), providerReviewInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'providerReview.label', default: 'ProviderReview'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
