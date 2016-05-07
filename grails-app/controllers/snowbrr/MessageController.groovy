package snowbrr

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_CONSUMER', 'ROLE_PROVIDER', 'ROLE_ADMIN'])
class MessageController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def springSecurityService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def messages = Message.findAllByUser(springSecurityService.currentUser)

        respond messages.sort().reverse(), model: [messageInstanceCount: messages.size()]
    }

    def show(Message messageInstance) {
        if (messageInstance == null) {
            notFound()
            return
        }
        messageInstance.read = true
        save messageInstance
        respond messageInstance
    }

    def unread() {
        render Message.findAllByUser(springSecurityService.currentUser).findAll{!it.read}.size()
    }

    def create() {
        params << [ from: springSecurityService.currentUser ]
        respond new Message(params)
    }

    @Transactional
    def save(Message messageInstance) {
        if (messageInstance == null) {
            notFound()
            return
        }

        if (messageInstance.hasErrors()) {
            respond messageInstance.errors, view: 'create'
            return
        }

        messageInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'message.label', default: 'Message'), messageInstance.id])
                redirect messageInstance
            }
            '*' { respond messageInstance, [status: CREATED] }
        }
    }

    def edit(Message messageInstance) {
        respond messageInstance
    }

    @Transactional
    def update(Message messageInstance) {
        if (messageInstance == null) {
            notFound()
            return
        }

        if (messageInstance.hasErrors()) {
            respond messageInstance.errors, view: 'edit'
            return
        }

        messageInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Message.label', default: 'Message'), messageInstance.id])
                redirect messageInstance
            }
            '*' { respond messageInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Message messageInstance) {

        if (messageInstance == null) {
            notFound()
            return
        }

        messageInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Message.label', default: 'Message'), messageInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'message.label', default: 'Message'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
