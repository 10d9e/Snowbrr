package snowbrr

import grails.buildtestdata.mixin.Build
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.*
import spock.lang.*

@TestFor(TransactionController)
@Mock(Transaction)
@Build([ Provider, Consumer, User ])
class TransactionControllerSpec extends Specification {

    def imageBytes = new File("test/unit/snowbrr/driveway.jpg").bytes

    def populateValidParams(params) {
        assert params != null
        params << [consumer: Consumer.build(), provider: Provider.build(), price: 50, status: "In Progress", finishBy: new Date() + 1, consumerNotes: "I would like to have this done before the big storm"]
    }

    void "Test the index action returns the correct model"() {

        given:
        def springSecurityService = mockFor(SpringSecurityService)
        springSecurityService.demand.currentUser{
            User.build()
        }
        controller.springSecurityService = springSecurityService.createMock()

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.transactionInstanceList
        model.transactionInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.transactionInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        given:
        def springSecurityService = mockFor(SpringSecurityService)
        springSecurityService.demand.currentUser{
            User.build()
        }
        springSecurityService.demand.currentUser{
            User.build()
        }
        controller.springSecurityService = springSecurityService.createMock()

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def transaction = new Transaction()
        transaction.validate()
        controller.save(transaction)

        then: "The create view is rendered again with the correct model"
        model.transactionInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        transaction = new Transaction(params)

        transaction.save()

        controller.save(transaction)

        then: "A redirect is issued to the show action"
        controller.flash.message != null
        Transaction.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def transaction = new Transaction(params)
        controller.show(transaction)

        then: "A model is populated containing the domain instance"
        model.transactionInstance == transaction
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def transaction = new Transaction(params)
        controller.edit(transaction)

        then: "A model is populated containing the domain instance"
        model.transactionInstance == transaction
    }

    void "Test the update action performs an update on a valid domain instance"() {

        given:
        def springSecurityService = mockFor(SpringSecurityService)
        springSecurityService.demand.currentUser{
            User.build()
        }
        springSecurityService.demand.currentUser{
            User.build()
        }
        controller.springSecurityService = springSecurityService.createMock()

        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/transaction/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def transaction = new Transaction()
        transaction.validate()
        controller.update(transaction)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.transactionInstance == transaction


        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        transaction = new Transaction(params).save(flush: true)
        controller.update(transaction)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/transaction/show/$transaction.id"
        flash.message != null
    }


    void "Test the providerComplete action performs an update on a valid domain instance"() {

        given:
        def springSecurityService = mockFor(SpringSecurityService)
        springSecurityService.demand.currentUser{
            User.build()
        }
        springSecurityService.demand.currentUser{
            User.build()
        }
        controller.springSecurityService = springSecurityService.createMock()

        def messageService = mockFor(MessageService)
        messageService.demand.send(){ }
        messageService.demand.send(){ }
        controller.messageService = messageService.createMock()

        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.providerComplete(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/transaction/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def transaction = new Transaction()
        transaction.validate()
        controller.providerComplete(transaction)

        then: "The show view is rendered again with the invalid instance"
        view == 'show'
        model.transactionInstance == transaction


        when: "An invalid domain instance state is passed to the update action"
        response.reset()
        populateValidParams(params)
        transaction = new Transaction(params)
        transaction.status = 'Request'
        transaction.save(flush:true)
        transaction.validate()
        controller.providerComplete(transaction)

        then: "The edit view is rendered again with the invalid instance"
        view == 'show'
        flash.message != null

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        transaction = new Transaction(params).save(flush: true)
        transaction.validate()
        controller.providerComplete(transaction)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/transaction/show/$transaction.id"
        flash.message != null
    }

    void "Test the providerAccept action performs an update on a valid domain instance"() {

        given:
        def springSecurityService = mockFor(SpringSecurityService)
        springSecurityService.demand.currentUser{
            User.build()
        }
        springSecurityService.demand.currentUser{
            User.build()
        }
        controller.springSecurityService = springSecurityService.createMock()

        def messageService = mockFor(MessageService)
        messageService.demand.send(){ }
        messageService.demand.send(){ }
        controller.messageService = messageService.createMock()

        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.providerAccept(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/transaction/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def transaction = new Transaction()
        transaction.validate()
        controller.providerAccept(transaction)

        then: "The show view is rendered again with the invalid instance"
        view == 'show'
        model.transactionInstance == transaction


        when: "An invalid domain instance state is passed to the update action"
        response.reset()
        populateValidParams(params)
        transaction = new Transaction(params)
        transaction.status = 'Request'
        transaction.save(flush:true)
        transaction.validate()
        controller.providerAccept(transaction)

        then: "The edit view is rendered again with the invalid instance"
        view == 'show'
        flash.message != null

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        transaction = new Transaction(params).save(flush: true)
        controller.providerAccept(transaction)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/transaction/show/$transaction.id"
        flash.message != null
    }


    void "Test the providerCancel action performs an update on a valid domain instance"() {

        given:
        def springSecurityService = mockFor(SpringSecurityService)
        springSecurityService.demand.currentUser{
            User.build()
        }
        springSecurityService.demand.currentUser{
            User.build()
        }
        controller.springSecurityService = springSecurityService.createMock()

        def messageService = mockFor(MessageService)
        messageService.demand.send(){ }
        messageService.demand.send(){ }
        controller.messageService = messageService.createMock()

        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.providerCancel(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/transaction/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def transaction = new Transaction()
        transaction.validate()
        controller.providerCancel(transaction)

        then: "The show view is rendered again with the invalid instance"
        view == 'show'
        model.transactionInstance == transaction


        when: "An invalid domain instance state is passed to the update action"
        response.reset()
        populateValidParams(params)
        transaction = new Transaction(params)
        transaction.status = 'Complete'
        transaction.save(flush:true)
        transaction.validate()
        controller.providerCancel(transaction)

        then: "The edit view is rendered again with the invalid instance"
        view == 'show'
        flash.message != null

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        transaction = new Transaction(params).save(flush: true)
        controller.providerCancel(transaction)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/transaction/show/$transaction.id"
        flash.message != null
    }

    void "Test the consumerCancel action performs an update on a valid domain instance"() {

        given:
        def springSecurityService = mockFor(SpringSecurityService)
        springSecurityService.demand.currentUser{
            User.build()
        }
        springSecurityService.demand.currentUser{
            User.build()
        }
        controller.springSecurityService = springSecurityService.createMock()

        def messageService = mockFor(MessageService)
        messageService.demand.send(){ }
        messageService.demand.send(){ }
        controller.messageService = messageService.createMock()

        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.consumerCancel(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/transaction/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def transaction = new Transaction()
        transaction.validate()
        controller.consumerCancel(transaction)

        then: "The show view is rendered again with the invalid instance"
        view == 'show'
        model.transactionInstance == transaction


        when: "An invalid domain instance state is passed to the update action"
        response.reset()
        populateValidParams(params)
        transaction = new Transaction(params)
        transaction.status = 'Complete'
        transaction.save(flush:true)
        transaction.validate()
        controller.consumerCancel(transaction)

        then: "The edit view is rendered again with the invalid instance"
        view == 'show'
        flash.message != null

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        transaction = new Transaction(params).save(flush: true)
        controller.consumerCancel(transaction)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/transaction/show/$transaction.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/transaction/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def transaction = new Transaction(params).save(flush: true)

        then: "It exists"
        Transaction.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(transaction)

        then: "The instance is deleted"
        Transaction.count() == 0
        response.redirectedUrl == '/transaction/index'
        flash.message != null
    }

    void "Test that the uploadImage action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def transaction = new Transaction(params).save()
        request.addFile('photoProof', imageBytes)
        controller.uploadImage(transaction)

        then: "A model is populated containing the domain instance"
        flash.message != null
    }
}
