package snowbrr.pages

import geb.Module
import geb.Page

class MessagesPage extends Page {

    static url = "message/index"

    static at = { title == "Message List" }

    static content = {
        logoutButton { $("#logout") }
        transactionsBadge { $("#trans") }
        unMessageBadge { $("#unread") }
        transactionButton { $("a[href='/Snowbrr/transaction/show/3']") }
        messageItems { moduleList MessageRow, $("table tr").tail() }
    }
}

class MessageRow extends Module {
    static content = {
        cell { $("td", it) }
        message { cell(0).text() }
        messageLink { cell(0).find('a') }
        from { cell(1).text() }
    }
}