package snowbrr.pages

import geb.Page

class LoginPage extends Page {

    static url = "login/auth"

    static at = { title == "Login" }

    static content = {
        loginForm { $("#loginForm") }
        loginButton { $("#submit") }
        registerLink { $("a[href*='register/index']") }
    }
}