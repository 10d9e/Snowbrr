package snowbrr

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(Message)
@Build(User)
class MessageSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test create successful Message"() {
        when:
        def message = new Message( from: User.build(), user: User.build(), timestamp: new Date(), content: 'Hello world' )

        then:
        assertTrue message.validate()
    }

    void "test create Message fails when user is null"() {
        when:
        def message = new Message( timestamp: new Date(), content: 'Hello world' )

        then:
        assertFalse message.validate()
        assertTrue message.errors.hasFieldErrors('user')
    }

    void "test create Message fails when timestamp is null"() {
        when:
        def message = new Message( from: User.build(), user: User.build(), content: 'Hello world' )

        then:
        assertFalse message.validate()
        assertTrue message.errors.hasFieldErrors('timestamp')
    }

    void "test create Message fails when content is null"() {
        when:
        def message = new Message( from: User.build(), user: User.build(), timestamp: new Date() )

        then:
        assertFalse message.validate()
        assertTrue message.errors.hasFieldErrors('content')
    }

}
