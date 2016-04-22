package snowbrr

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(Driveway)
@Build(Consumer)
class DrivewaySpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test create validate Driveway"() {
        when:

        def driveway = new Driveway(length: 12, width: 43, consumer: Consumer.build())
        driveway.validate()

        then:
        assertTrue driveway.validate()
    }
}
