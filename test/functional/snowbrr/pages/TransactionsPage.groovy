package snowbrr.pages

import geb.Module
import geb.Page

class TransactionsPage extends Page {

    static url = "transaction/index"

    static at = { title == "Transaction List" }

    static content = {
        logoutButton { $("#logout") }
        transactionsBadge { $("#trans") }
        unMessageBadge { $("#unread") }
        transactionButton { $("a[href='/Snowbrr/transaction/show/3']") }
        transactionItems { moduleList TransactionRow, $("table tr").tail() }
    }
}

class TransactionRow extends Module {
    static content = {
        cell { $("td", it) }
        view { cell(0).text() }
        prodider { cell(1).text() }
        status { cell(2).text() }
    }
}