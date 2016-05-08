package snowbrr

class TransactionLabelTagLib {
    static namespace = "trans"

    /**
     * @attr transaction REQUIRED
     */
    def label = { attrs ->

        switch (attrs.transaction.status) {

            case 'Request':
                out << "<h4 style='position: relative;top: -10px;' ><span class=\"label label-info\">${attrs.transaction.status}</span></h4>"
                break

            case 'Cancelled':
                out << "<h4 style='position: relative;top: -10px;' ><span class=\"label label-danger\">${attrs.transaction.status}</span></h4>"
                break

            case 'Price Change':
                out << "<h4 style='position: relative;top: -10px;' ><span class=\"label label-info\">${attrs.transaction.status}</span></h4>"
                break

            case 'In Progress':
                out << "<h4 style='position: relative;top: -10px;' ><span class=\"label label-primary\">${attrs.transaction.status}</span></h4>"
                break

            case 'Complete':
                out << "<h4 style='position: relative;top: -10px;' ><span class=\"label label-success\">${attrs.transaction.status}</span></h4>"
                break

            default:
                out << "<h4 style='position: relative;top: -10px;' ><span class=\"label label-default\">${attrs.transaction.status}</span></h4>"
                break

        }

    }
}
