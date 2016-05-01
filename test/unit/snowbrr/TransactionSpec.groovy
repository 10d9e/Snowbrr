package snowbrr

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(Transaction)
@Build([ Provider, Consumer ])
class TransactionSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test create valid Transaction"() {
        when:
        def transaction = new Transaction(consumer: Consumer.build(), provider: Provider.build(), price: 50, status: "In Progress", finishBy: new Date() + 1, consumerNotes: "I would like to have this done before the big storm")

        then:
        assertTrue transaction.validate()
    }

    void "test Transaction validates when consumer is null"() {
        when:
        def transaction = new Transaction(provider: Provider.build(), price: 50, status: "In Progress", finishBy: new Date() + 1, consumerNotes: "I would like to have this done before the big storm")

        then:
        assertTrue transaction.validate()
    }

    void "test Transaction fails when status is null"() {
        when:
        def transaction = new Transaction(consumer: Consumer.build(), provider: Provider.build(), price: 50, status: null, finishBy: new Date() + 1, consumerNotes: "I would like to have this done before the big storm")

        then:
        assertFalse transaction.validate()
        assertTrue transaction.errors.hasFieldErrors('status')
    }

    void "test Transaction fails when status is invalid"() {
        when:
        def transaction = new Transaction(consumer: Consumer.build(), provider: Provider.build(), price: 50, status: "jsdklsdf", finishBy: new Date() + 1, consumerNotes: "I would like to have this done before the big storm")

        then:
        assertFalse transaction.validate()
        assertTrue transaction.errors.hasFieldErrors('status')
    }

    void "test Transaction fails when finishBy is null"() {
        when:
        def transaction = new Transaction(consumer: Consumer.build(), provider: Provider.build(), price: 50, status: "In Progress", consumerNotes: "I would like to have this done before the big storm")

        then:
        assertFalse transaction.validate()
        assertTrue transaction.errors.hasFieldErrors('finishBy')
    }

    void "test Transaction fails when finishBy is invalid"() {
        when:
        def transaction = new Transaction(consumer: Consumer.build(), provider: Provider.build(), price: 50, status: "In Progress", finishBy: new Date() - 1, consumerNotes: "I would like to have this done before the big storm")

        then:
        assertFalse transaction.validate()
        assertTrue transaction.errors.hasFieldErrors('finishBy')
    }

}
