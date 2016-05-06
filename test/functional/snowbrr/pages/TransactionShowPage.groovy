package snowbrr.pages

import geb.Page

class TransactionShowPage extends Page {

    static url = "/transaction/index"

    static at = { title == "Show Transaction" }

    static content = {
        logoutButton { $("#logout") }
        transactionsBadge { $("#trans") }
        unMessageBadge { $("#unread") }
        acceptButton { $("a[href='/Snowbrr/transaction/providerAccept/3']") }
        completeButton { $("a[href='/Snowbrr/transaction/providerComplete/3']") }
    }
}