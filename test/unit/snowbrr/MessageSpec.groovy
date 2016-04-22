package snowbrr

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(Message)
class MessageSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test create successful Message"() {
        when:
        def message = new Message( fromUsername: 'test', timestamp: new Date(), content: 'Hello world' )

        then:
        assertTrue message.validate()
    }

    void "test create Message fails when fromUsername is null"() {
        when:
        def message = new Message( timestamp: new Date(), content: 'Hello world' )

        then:
        assertFalse message.validate()
        assertTrue message.errors.hasFieldErrors('fromUsername')
    }

    void "test create Message fails when timestamp is null"() {
        when:
        def message = new Message( fromUsername: 'test', content: 'Hello world' )

        then:
        assertFalse message.validate()
        assertTrue message.errors.hasFieldErrors('timestamp')
    }

    void "test create Message fails when content is null"() {
        when:
        def message = new Message( fromUsername: 'test', timestamp: new Date() )

        then:
        assertFalse message.validate()
        assertTrue message.errors.hasFieldErrors('content')
    }

}
