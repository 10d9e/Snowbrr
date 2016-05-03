package snowbrr

import grails.buildtestdata.mixin.Build
import grails.test.mixin.*
import spock.lang.*

@TestFor(ProviderReviewController)
@Mock(ProviderReview)
@Build([ Provider, Consumer ])
class ProviderReviewControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params << [ provider: Provider.build(), reviewer: Consumer.build(), rating: 4.5, content: "Customer was great, cars were moved and customer was very nice.", timestamp: new Date() ]
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.providerReviewInstanceList
        model.providerReviewInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.providerReviewInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        given:
        def messageService = mockFor(MessageService)
        messageService.demand.send{}
        controller.messageService = messageService.createMock()

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def providerReview = new ProviderReview()
        providerReview.validate()
        controller.save(providerReview)

        then: "The create view is rendered again with the correct model"
        model.providerReviewInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        providerReview = new ProviderReview(params)

        controller.save(providerReview)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/providerReview/show/1'
        controller.flash.message != null
        ProviderReview.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def providerReview = new ProviderReview(params)
        controller.show(providerReview)

        then: "A model is populated containing the domain instance"
        model.providerReviewInstance == providerReview
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def providerReview = new ProviderReview(params)
        controller.edit(providerReview)

        then: "A model is populated containing the domain instance"
        model.providerReviewInstance == providerReview
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/providerReview/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def providerReview = new ProviderReview()
        providerReview.validate()
        controller.update(providerReview)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.providerReviewInstance == providerReview

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        providerReview = new ProviderReview(params).save(flush: true)
        controller.update(providerReview)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/providerReview/show/$providerReview.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/providerReview/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def providerReview = new ProviderReview(params).save(flush: true)

        then: "It exists"
        ProviderReview.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(providerReview)

        then: "The instance is deleted"
        ProviderReview.count() == 0
        response.redirectedUrl == '/providerReview/index'
        flash.message != null
    }
}
