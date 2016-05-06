package snowbrr.pages

import geb.Page

class ProviderReviewPage extends Page {

    static url = "providerReview/create?reviewer=6&provider=5"

    static at = {
        title == "Create Provider Review"
    }
    static content = {
        createButton { $("#create") }
        starFour{ $("label[for*='star4']") }
        starOne{ $("label[for*='star1']") }
        textContent{ $("#content") }

        logoutButton { $("#logout") }
    }

}