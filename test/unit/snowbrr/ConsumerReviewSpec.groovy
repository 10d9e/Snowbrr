package snowbrr

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(ConsumerReview)
@Mock([User, Role, UserRole])
@Build([Consumer, Provider])
class ConsumerReviewSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test create valid ConsumerReview"() {
        when:
        def review = new ConsumerReview( consumer: Consumer.build(), reviewer: Provider.build(), timestamp: new Date(), rating: 4, content: "Did a great job, I can see my pavement again!" )

        then:
        assertTrue review.validate()
    }

    void "test create ConsumerReview fails with null consumer"() {
        when:
        def review = new ConsumerReview( reviewer: Provider.build(), timestamp: new Date(), rating: 4, content: "Did a great job, I can see my pavement again!" )

        then:
        assertFalse review.validate()
        assertTrue review.errors.hasFieldErrors('consumer')
    }

    void "test create ConsumerReview fails with null reviewer"() {
        when:
        def review = new ConsumerReview( consumer: Consumer.build(), timestamp: new Date(), rating: 4, content: "Did a great job, I can see my pavement again!" )

        then:
        assertFalse review.validate()
        assertTrue review.errors.hasFieldErrors('reviewer')
    }

    void "test create ConsumerReview fails when timestamp is null"() {
        when:
        def review = new ConsumerReview( consumer: Consumer.build(), reviewer: Provider.build(), rating: 4, content: "Did a great job, I can see my pavement again!" )

        then:
        assertFalse review.validate()
        assertTrue review.errors.hasFieldErrors('timestamp')
    }

    void "test create ConsumerReview fails when rating is outside range"() {
        when:
        def review = new ConsumerReview( consumer: Consumer.build(), reviewer: Provider.build(), timestamp: new Date(), rating: 46, content: "Did a great job, I can see my pavement again!" )

        then:
        assertFalse review.validate()
        assertTrue review.errors.hasFieldErrors('rating')
    }

}
