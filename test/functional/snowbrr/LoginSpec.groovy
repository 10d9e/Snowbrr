package snowbrr

import snowbrr.pages.HomePage
import snowbrr.pages.LoginPage
import geb.spock.GebReportingSpec
import spock.lang.Stepwise

@Stepwise
class LoginSpec extends GebReportingSpec {

    def "invalid login"() {
        given: "I am at the login page"
        to LoginPage

        when: "I am entering invalid password"
        loginForm.j_username = "snuffleuffagus"
        loginForm.j_password = "ioguffwf"
        loginButton.click()

        then: "I am being redirected to the login page"
        at LoginPage
        !loginForm.j_username
        !loginForm.j_password
    }

    def "customer login"() {
        given : "I am at the login page"
        to LoginPage

        when: "I am entering valid username and password"
        loginForm.j_username = "jay"
        loginForm.j_password = "jay"
        loginButton.click()

        then: "I am being redirected to the homepage"
        at HomePage
        logoutButton.click()
    }

    def "provider login"() {
        given : "I am at the login page"
        to LoginPage

        when: "I am entering valid username and password"
        loginForm.j_username = "maryse"
        loginForm.j_password = "maryse"
        loginButton.click()

        then: "I am being redirected to the homepage"
        at HomePage
        logoutButton.click()
    }


}
