package snowbrr

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_CONSUMER', 'ROLE_PROVIDER'])

class ConsumerReviewController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ConsumerReview.list(params), model: [consumerReviewInstanceCount: ConsumerReview.count()]
    }

    def show(ConsumerReview consumerReviewInstance) {
        respond consumerReviewInstance
    }

    def create() {
        respond new ConsumerReview(params)
    }

    @Transactional
    def save(ConsumerReview consumerReviewInstance) {
        if (consumerReviewInstance == null) {
            notFound()
            return
        }

        if (consumerReviewInstance.hasErrors()) {
            respond consumerReviewInstance.errors, view: 'create'
            return
        }

        consumerReviewInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'consumerReview.label', default: 'ConsumerReview'), consumerReviewInstance.id])
                redirect consumerReviewInstance
            }
            '*' { respond consumerReviewInstance, [status: CREATED] }
        }
    }

    def edit(ConsumerReview consumerReviewInstance) {
        respond consumerReviewInstance
    }

    @Transactional
    def update(ConsumerReview consumerReviewInstance) {
        if (consumerReviewInstance == null) {
            notFound()
            return
        }

        if (consumerReviewInstance.hasErrors()) {
            respond consumerReviewInstance.errors, view: 'edit'
            return
        }

        consumerReviewInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ConsumerReview.label', default: 'ConsumerReview'), consumerReviewInstance.id])
                redirect consumerReviewInstance
            }
            '*' { respond consumerReviewInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ConsumerReview consumerReviewInstance) {

        if (consumerReviewInstance == null) {
            notFound()
            return
        }

        consumerReviewInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ConsumerReview.label', default: 'ConsumerReview'), consumerReviewInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'consumerReview.label', default: 'ConsumerReview'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
