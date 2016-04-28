package snowbrr

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(Provider)
@Build(User)
class ProviderSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test create valid Provider"() {
        when:
        def provider = new Provider(user: new User (username: 'jay', password: 'jay', firstname: "Maryse", lastname: "Arseneau", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "maryse.g.arseneau@gmail.com", country: "Canada", latitude: 12, longitude: 12), companyName: "Snow Blowers Inc")

        then:
        assertTrue provider.user.validate()
        assertTrue provider.validate()
    }

    void "test Provider fails when user is null"(){
        when:
        def provider = new Provider(companyName: "Snow Blowers Inc")

        then:
        assertFalse provider.validate()
        assertTrue provider.errors.hasFieldErrors('user')
    }

    void "test Provider fails when firstname is null"() {
        when:
        def provider = new Provider(user: new User (username: 'jay', password: 'jay', lastname: "Arseneau", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "maryse.g.arseneau@gmail.com", country: "Canada", latitude: 12, longitude: 12), companyName: "Snow Blowers Inc")

        then:
        assertFalse provider.user.validate()
        assertTrue provider.user.errors.hasFieldErrors('firstname')
    }

    void "test Provider fails when lastname is null"() {
        when:
        def provider = new Provider(user: new User (username: 'jay', password: 'jay', firstname: "Maryse", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "maryse.g.arseneau@gmail.com", country: "Canada", latitude: 12, longitude: 12), companyName: "Snow Blowers Inc")

        then:
        assertFalse provider.user.validate()
        assertTrue provider.user.errors.hasFieldErrors('lastname')
    }

    void "test Provider fails when address is null"() {
        when:
        def provider = new Provider(user: new User (username: 'jay', password: 'jay', firstname: "Maryse", lastname: "Arseneau", city: "Dieppe",
                province: "NB", email: "maryse.g.arseneau@gmail.com", country: "Canada", latitude: 12, longitude: 12), companyName: "Snow Blowers Inc")

        then:
        assertFalse provider.user.validate()
        assertTrue provider.user.errors.hasFieldErrors('address')
    }

    void "test Provider fails when city is null"() {
        when:
        def provider = new Provider(user: new User (username: 'jay', password: 'jay', firstname: "Maryse", lastname: "Arseneau", address: "421 Vanier Street",
                province: "NB", email: "maryse.g.arseneau@gmail.com", country: "Canada", latitude: 12, longitude: 12), companyName: "Snow Blowers Inc")

        then:
        assertFalse provider.user.validate()
        assertTrue provider.user.errors.hasFieldErrors('city')
    }

    void "test Provider fails when province is null"() {
        when:
        def provider = new Provider(user: new User (username: 'jay', password: 'jay', firstname: "Maryse", lastname: "Arseneau", address: "421 Vanier Street", city: "Dieppe",
                email: "maryse.g.arseneau@gmail.com", country: "Canada", latitude: 12, longitude: 12), companyName: "Snow Blowers Inc")

        then:
        assertFalse provider.user.validate()
        assertTrue provider.user.errors.hasFieldErrors('province')
    }

    void "test Provider fails when email is invalid"() {
        when:
        def provider = new Provider(user: new User (username: 'jay', password: 'jay', firstname: "Maryse", lastname: "Arseneau", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "dfhgh", country: "Canada", latitude: 12, longitude: 12), companyName: "Snow Blowers Inc")

        then:
        assertFalse provider.user.validate()
        assertTrue provider.user.errors.hasFieldErrors('email')
    }

    void "test Provider fails when country is null"() {
        when:
        def provider = new Provider(user: new User (username: 'jay', password: 'jay', firstname: "Maryse", lastname: "Arseneau", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "maryse.g.arseneau@gmail.com", latitude: 12, longitude: 12), companyName: "Snow Blowers Inc")

        then:
        assertFalse provider.user.validate()
        assertTrue provider.user.errors.hasFieldErrors('country')
    }

    void "test Provider fails when country is not Canada or United States"() {
        when:
        def provider = new Provider(user: new User (username: 'jay', password: 'jay', firstname: "Maryse", lastname: "Arseneau", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "maryse.g.arseneau@gmail.com", country: "Spain", latitude: 12, longitude: 12), companyName: "Snow Blowers Inc")

        then:
        assertFalse provider.user.validate()
        assertTrue provider.user.errors.hasFieldErrors('country')
    }

    void "test Provider fails when companyName is null"() {
        when:
        def provider = new Provider(user: new User (username: 'jay', password: 'jay', firstname: "Maryse", lastname: "Arseneau", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "maryse.g.arseneau@gmail.com", country: "Canada", latitude: 12, longitude: 12))

        then:
        assertFalse provider.validate()
        assertTrue provider.errors.hasFieldErrors('companyName')
    }
}
