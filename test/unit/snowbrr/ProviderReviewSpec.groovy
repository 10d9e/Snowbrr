package snowbrr

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(ProviderReview)
@Build([Provider, Consumer])
class ProviderReviewSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test create valid ProviderReview"() {
        when:
            def review = new ProviderReview( provider: Provider.build(), reviewer: Consumer.build(), rating: 4.5, content: "Customer was great, cars were moved and customer was very nice.", timestamp: new Date() )
            review.validate()
        then:
            assertTrue review.validate()
    }

    void "test create ProviderReview fails with null provider"() {
        when:
        def review = new ProviderReview( reviewer: Consumer.build(), rating: 4.5, content: "Customer was great, cars were moved and customer was very nice.", timestamp: new Date() )

        then:
        assertFalse review.validate()
        assertTrue review.errors.hasFieldErrors('provider')
    }

    void "test create ProviderReview fails with null reviewer"() {
        when:
        def review = new ProviderReview( provider: Provider.build(), rating: 4.5, content: "Customer was great, cars were moved and customer was very nice.", timestamp: new Date() )

        then:
        assertFalse review.validate()
        assertTrue review.errors.hasFieldErrors('reviewer')
    }

    void "test create ProviderReview fails when rating is out of range"() {
        when:
        def review = new ProviderReview( provider: Provider.build(), reviewer: Consumer.build(), rating: 5743, content: "Customer was great, cars were moved and customer was very nice.", timestamp: new Date() )

        then:
        assertFalse review.validate()
        assertTrue review.errors.hasFieldErrors('rating')
    }

    void "test create ProviderReview fails when timestamp is null"() {
        when:
        def review = new ProviderReview( provider: Provider.build(), reviewer: Consumer.build(), rating: 4.5, content: "Customer was great, cars were moved and customer was very nice." )
        review.validate()
        then:
        assertFalse review.validate()
        assertTrue review.errors.hasFieldErrors('timestamp')
    }

}
