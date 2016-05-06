package snowbrr.pages

import geb.Module
import geb.Page

class ConsumerReviewPage extends Page {

    static url = "consumerReview/create?reviewer=5&consumer=6"

    static at = {
        title == "Create Consumer Review"
    }
    static content = {
        createButton { $("#create") }
        starFour{ $("label[for*='star4']") }
        starOne{ $("label[for*='star1']") }
        textContent{ $("#content") }

        logoutButton { $("#logout") }
    }

}