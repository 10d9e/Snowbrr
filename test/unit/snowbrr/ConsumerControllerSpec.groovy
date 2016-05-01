package snowbrr

import grails.buildtestdata.mixin.Build
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.*
import spock.lang.*

@TestFor(ConsumerController)
@Mock(Consumer)
@Build([ Driveway, User ])
class ConsumerControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params << [user: User.build(username: 'user-' + new Date()), driveway: Driveway.build() ]
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.consumerInstanceList
        model.consumerInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.consumerInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def consumer = new Consumer()
        consumer.validate()
        controller.save(consumer)

        then: "The create view is rendered again with the correct model"
        model.consumerInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        consumer = new Consumer(params)

        controller.save(consumer)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/consumer/show/2'
        controller.flash.message != null
        Consumer.count() == 2
    }

    void "Test that the show action returns the correct model"() {
        given:
        def springSecurityService = mockFor(SpringSecurityService)
        springSecurityService.demand.currentUser{
            User.build()
        }
        controller.springSecurityService = springSecurityService.createMock()

        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def consumer = new Consumer(params)
        controller.show(consumer)

        then: "A model is populated containing the domain instance"
        model.consumerInstance == consumer
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def consumer = new Consumer(params)
        controller.edit(consumer)

        then: "A model is populated containing the domain instance"
        model.consumerInstance == consumer
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/consumer/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def consumer = new Consumer()
        consumer.validate()
        controller.update(consumer)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.consumerInstance == consumer

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        consumer = new Consumer(params).save(flush: true)
        controller.update(consumer)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/consumer/show/$consumer.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/consumer/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def consumer = new Consumer(params).save(flush: true)

        then: "It exists"
        Consumer.count() == 2

        when: "The domain instance is passed to the delete action"
        controller.delete(consumer)

        then: "The instance is deleted"
        Consumer.count() == 1
        response.redirectedUrl == '/consumer/index'
        flash.message != null
    }
}
