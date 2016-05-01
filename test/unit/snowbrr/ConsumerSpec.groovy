package snowbrr

import grails.buildtestdata.mixin.Build
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(Consumer)
@Build(User)
class ConsumerSpec extends Specification {


    def setup() {
    }

    def cleanup() {
    }

    void "test create valid consumer"() {

        when:
        Consumer consumer = new Consumer(user: new User( username: "jay", password: "jay", firstname: "Jay", lastname: "Logelin", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "jay.logelin@gmail.com", country: "Canada", latitude: 12, longitude: 12), driveway: new Driveway(length: 100, width: 75))

        consumer.user.addToMessages( new Message(from: User.build(), user: User.build(), content: "This is a message", timestamp: new Date()) )

        then:
        assertTrue consumer.validate()
    }

    void "test create consumer fails when user is null"() {

        when:
        Consumer consumer = new Consumer( driveway: new Driveway(length: 100, width: 75))

        then:
        assertFalse consumer.validate()
    }

    void "test create consumer fails when email is invalid"() {

        when:
        Consumer consumer = new Consumer(user: new User( username: "jay", password: "jay", firstname: "Jay", lastname: "Logelin", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "dfghfgh", country: "Canada", latitude: 12, longitude: 12), driveway: new Driveway(length: 100, width: 75))

        consumer.user.addToMessages(new Message(from: User.build(), user: User.build(), content: "This is a message", timestamp: new Date()) )

        then:
        assertFalse consumer.user.validate()
        assertTrue consumer.user.errors.hasFieldErrors('email')
    }

    void "test create consumer fails when firstname is null"() {

        when:
        Consumer consumer = new Consumer(user: new User( username: "jay", password: "jay", lastname: "Logelin", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "jay.logelin@gmail.com", country: "Canada", latitude: 12, longitude: 12), driveway: new Driveway(length: 100, width: 75))

        consumer.user.addToMessages(new Message(from: User.build(), user: User.build(), content: "This is a message", timestamp: new Date()) )

        then:
        assertFalse consumer.user.validate()
        assertTrue consumer.user.errors.hasFieldErrors('firstname')
    }

    void "test create consumer fails when lastname is null"() {

        when:
        Consumer consumer = new Consumer(user: new User( username: "jay", password: "jay", firstname: "Jay", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "jay.logelin@gmail.com", country: "Canada", latitude: 12, longitude: 12), driveway: new Driveway(length: 100, width: 75))

        consumer.user.addToMessages(new Message(from: User.build(), user: User.build(), content: "This is a message", timestamp: new Date()) )

        then:
        assertFalse consumer.user.validate()
        assertTrue consumer.user.errors.hasFieldErrors('lastname')
    }

    void "test create consumer fails when address is null"() {

        when:

        Consumer consumer = new Consumer(user: new User( username: "jay", password: "jay", firstname: "Jay", lastname: "Logelin", city: "Dieppe",
                province: "NB", email: "jay.logelin@gmail.com", country: "Canada", latitude: 12, longitude: 12), driveway: new Driveway(length: 100, width: 75))

        consumer.user.addToMessages(new Message(from: User.build(), user: User.build(), content: "This is a message", timestamp: new Date()) )

        then:
        assertFalse consumer.user.validate()
        assertTrue consumer.user.errors.hasFieldErrors('address')
    }

    void "test create consumer fails when city is null"() {

        when:

        Consumer consumer = new Consumer(user: new User( username: "jay", password: "jay", firstname: "Jay", lastname: "Logelin", address: "421 Vanier Street",
                province: "NB", email: "jay.logelin@gmail.com", country: "Canada", latitude: 12, longitude: 12), driveway: new Driveway(length: 100, width: 75))

        consumer.user.addToMessages(new Message(from: User.build(), user: User.build(), content: "This is a message", timestamp: new Date()) )

        then:
        assertFalse consumer.user.validate()
        assertTrue consumer.user.errors.hasFieldErrors('city')
    }

    void "test create consumer fails when province is null"() {

        when:

        Consumer consumer = new Consumer(user: new User( username: "jay", password: "jay", firstname: "Jay", lastname: "Logelin", address: "421 Vanier Street", city: "Dieppe",
                email: "jay.logelin@gmail.com", country: "Canada", latitude: 12, longitude: 12), driveway: new Driveway(length: 100, width: 75))

        consumer.user.addToMessages(new Message(from: User.build(), user: User.build(), content: "This is a message", timestamp: new Date()) )

        then:
        assertFalse consumer.user.validate()
        assertTrue consumer.user.errors.hasFieldErrors('province')
    }

    void "test create consumer fails when country is null"() {

        when:

        Consumer consumer = new Consumer(user: new User( username: "jay", password: "jay", firstname: "Jay", lastname: "Logelin", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "jay.logelin@gmail.com", latitude: 12, longitude: 12), driveway: new Driveway(length: 100, width: 75))

        consumer.user.addToMessages(new Message(from: User.build(), user: User.build(), content: "This is a message", timestamp: new Date()) )

        then:
        assertFalse consumer.user.validate()
        assertTrue consumer.user.errors.hasFieldErrors('country')
    }

    void "test create consumer fails when country is not Canada or United States"() {

        when:

        Consumer consumer = new Consumer(user: new User( username: "jay", password: "jay", firstname: "Jay", lastname: "Logelin", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "jay.logelin@gmail.com", country: "Spain", latitude: 12, longitude: 12), driveway: new Driveway(length: 100, width: 75))

        consumer.user.addToMessages(new Message(from: User.build(), user: User.build(), content: "This is a message", timestamp: new Date()) )

        then:
        assertFalse consumer.user.validate()
        assertTrue consumer.user.errors.hasFieldErrors('country')
    }

    void "test create consumer fails when driveway is null"() {

        when:

        Consumer consumer = new Consumer(user: new User( username: "jay", password: "jay", firstname: "Jay", lastname: "Logelin", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "jay.logelin@gmail.com", country: "Canada", latitude: 12, longitude: 12))

        consumer.user.addToMessages(new Message(from: User.build(), user: User.build(), content: "This is a message", timestamp: new Date()) )

        then:
        assertFalse consumer.validate()
        assertTrue consumer.errors.hasFieldErrors('driveway')
    }

}
