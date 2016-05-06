package snowbrr.pages

import geb.Page

class HomePage extends Page {

    static url = "/"

    static at = { title == "Provider List" }

    static content = {
        logoutButton { $("#logout") }
        providerMaryseButton { $("#provider-id-5") }
        requestMaryseButton { $("#request-5") }
        transactionsBadge { $("#trans") }
        unMessageBadge { $("#unread") }
    }
}