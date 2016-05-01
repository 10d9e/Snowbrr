package snowbrr

import grails.buildtestdata.mixin.Build
import grails.test.GroovyPagesTestCase

@Build([Consumer, Provider])
class StatisticsTagLibTest extends GroovyPagesTestCase {

    def setup() {
    }

    def cleanup() {
    }

    void "test averageRating" () {

        when:

        Provider provider = Provider.build()

        5.times {
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: 4, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:averageRating provider="${provider}" />', [provider: provider]) == "4.0"
    }

    void "test averageRating again" () {

        when:

        Provider provider = Provider.build()

        [0,1,2,3,4,5].each{
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:averageRating provider="${provider}" />', [provider: provider]) == "2.5"
    }

    void "test zeroStarCount" () {

        when:

        Provider provider = Provider.build()

        [0,1,2,3,4,5].each{
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:zeroStarCount provider="${provider}" />', [provider: provider]) == "1"
    }

    void "test zeroStarPct" () {
        when:
        Provider provider = Provider.build()
        [0,1,2,3,4].each{
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }
        then:
        assert applyTemplate('<stats:zeroStarPct provider="${provider}" />', [provider: provider]) == "20.0"
    }

    void "test oneStarCount" () {

        when:

        Provider provider = Provider.build()

        [1,1,1,3,4,5].each{
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:oneStarCount provider="${provider}" />', [provider: provider]) == "3"
    }

    void "test oneStarPct" () {
        when:
        Provider provider = Provider.build()
        [0,1,2,3,4].each{
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }
        then:
        assert applyTemplate('<stats:oneStarPct provider="${provider}" />', [provider: provider]) == "20.0"
    }

    void "test twoStarCount" () {

        when:

        Provider provider = Provider.build()

        [2,1,2,2,4,5].each{
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:twoStarCount provider="${provider}" />', [provider: provider]) == "3"
    }

    void "test twoStarPct" () {
        when:
        Provider provider = Provider.build()
        [0,1,2,3,4].each{
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }
        then:
        assert applyTemplate('<stats:twoStarPct provider="${provider}" />', [provider: provider]) == "20.0"
    }

    void "test threeStarCount" () {

        when:

        Provider provider = Provider.build()

        [2,1,2,2,4,5].each{
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:threeStarCount provider="${provider}" />', [provider: provider]) == "0"
    }

    void "test threeStarPct" () {
        when:
        Provider provider = Provider.build()
        [0,1,2,3,4].each{
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }
        then:
        assert applyTemplate('<stats:threeStarPct provider="${provider}" />', [provider: provider]) == "20.0"
    }

    void "test fourStarCount" () {

        when:

        Provider provider = Provider.build()

        [2,1,2,2,4,5].each{
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:fourStarCount provider="${provider}" />', [provider: provider]) == "1"
    }

    void "test fourStarPct" () {
        when:
        Provider provider = Provider.build()
        [0,1,2,3,4].each{
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }
        then:
        assert applyTemplate('<stats:fourStarPct provider="${provider}" />', [provider: provider]) == "20.0"
    }

    void "test fiveStarCount" () {

        when:

        Provider provider = Provider.build()

        [5,5,5,2,5,5,5,5,5].each{
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:fiveStarCount provider="${provider}" />', [provider: provider]) == "8"
    }

    void "test fiveStarPct" () {
        when:
        Provider provider = Provider.build()
        [1,2,3,4,5].each{
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }
        then:
        assert applyTemplate('<stats:fiveStarPct provider="${provider}" />', [provider: provider]) == "20.0"
    }

    void "test reviewSize" () {

        when:

        Provider provider = Provider.build()

        [5,5,5,2,5,5,5,5,5].each{
            provider.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:reviewSize provider="${provider}" />', [provider: provider]) == "9"
    }

}
