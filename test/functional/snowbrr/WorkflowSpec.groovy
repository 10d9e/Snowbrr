package snowbrr

import geb.spock.GebReportingSpec
import snowbrr.pages.ConsumerReviewPage
import snowbrr.pages.HomePage
import snowbrr.pages.LoginPage
import snowbrr.pages.MessagesPage
import snowbrr.pages.ProviderReviewPage
import snowbrr.pages.TransactionShowPage
import snowbrr.pages.TransactionsPage
import spock.lang.Stepwise

@Stepwise
class WorkflowSpec extends GebReportingSpec {

    def "customer login"() {
        given : "I am at the login page"
        to LoginPage

        when: "I am entering valid username and password"
        loginForm.j_username = "jay"
        loginForm.j_password = "jay"
        loginButton.click()

        then: "I am being redirected to the homepage"
        at HomePage
        providerMaryseButton.click()
        requestMaryseButton.click()

        at HomePage
        transactionsBadge.text() == "1"
        unMessageBadge.text() == "1"
        logoutButton.click()
    }

    def "provider update transaction to accept"() {
        given : "I am at the login page"
        to LoginPage

        when: "I am entering valid username and password"
        loginForm.j_username = "maryse"
        loginForm.j_password = "maryse"
        loginButton.click()

        to TransactionsPage
        transactionButton.click()

        at TransactionShowPage
        acceptButton.click()

        at TransactionShowPage
        transactionsBadge.text() == "1"
//        unMessageBadge.text() == "2"

        then: "I am being redirected to the homepage"
        at TransactionShowPage
        logoutButton.click()
    }

    def "customer check transaction"() {
        given : "I am at the login page"
        to LoginPage

        when: "I am entering valid username and password"
        loginForm.j_username = "jay"
        loginForm.j_password = "jay"
        loginButton.click()

        then: "I am being redirected to the homepage"

        to TransactionsPage
        transactionsBadge.text() == "1"
        unMessageBadge.text() == "2"

        transactionItems[0].status == "In Progress"

        logoutButton.click()
    }

    def "provider update transaction to complete"() {
        given : "I am at the login page"
        to LoginPage

        when: "I am entering valid username and password"
        loginForm.j_username = "maryse"
        loginForm.j_password = "maryse"
        loginButton.click()

        to TransactionsPage
        transactionButton.click()

        at TransactionShowPage
        completeButton.click()

        at TransactionShowPage
        transactionsBadge.text() == "1"
//        unMessageBadge.text() == "2"

        then: "I am being redirected to the homepage"
        at TransactionShowPage
        logoutButton.click()
    }

    def "customer check transaction is closed"() {
        given : "I am at the login page"
        to LoginPage

        when: "I am entering valid username and password"
        loginForm.j_username = "jay"
        loginForm.j_password = "jay"
        loginButton.click()

        then: "I am being redirected to the homepage"
        to TransactionsPage
        transactionsBadge.text() == ""
        unMessageBadge.text() == "3"
        transactionItems[0].status == "Complete"
      //  logoutButton.click()

    }

    def "customer check messages"() {
        given: "I am at the Messages page"
        to MessagesPage

        when:
        unMessageBadge.text() == "3"
        messageItems.messageLink[0].click()

        to MessagesPage
        unMessageBadge.text() == "2"
        messageItems.messageLink[1].click()

        to MessagesPage
        unMessageBadge.text() == "1"
        messageItems.messageLink[2].click()

        to MessagesPage
        unMessageBadge.text() == ""

        then:
        logoutButton.click()

    }

    def "provider check messages"() {
        given: "I am at the login page"
        to LoginPage
        loginForm.j_username = "maryse"
        loginForm.j_password = "maryse"
        loginButton.click()
        to MessagesPage

        when: "I am entering valid username and password"
        messageItems.messageLink[0].click()

        to MessagesPage
        messageItems.messageLink[1].click()

        to MessagesPage
        messageItems.messageLink[2].click()

        to MessagesPage

        then:
        logoutButton.click()
    }

    def "provider rate customer"() {
        given: "I am at the login page"
        to LoginPage
        loginForm.j_username = "maryse"
        loginForm.j_password = "maryse"
        loginButton.click()
        to ConsumerReviewPage

        when:
        starFour.click()
        textContent = 'Wow what a fantastic customer. Everything went really really well!'
        createButton.click()

        then:



        logoutButton.click()

    }

    def "customer rate provider"() {
        given: "I am at the login page"
        to LoginPage
        loginForm.j_username = "jay"
        loginForm.j_password = "jay"
        loginButton.click()
        to ProviderReviewPage

        when:
        starOne.click()
        textContent = 'Horrible job, and on top of that, he ran over the dog!'
        createButton.click()

        then:
        logoutButton.click()

    }

}
