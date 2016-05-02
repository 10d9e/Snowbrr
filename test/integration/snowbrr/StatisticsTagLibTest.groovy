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

        Provider target = Provider.build()

        5.times {
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: 4, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:averageRating target="${target}" />', [target: target]) == "4.0"
    }

    void "test averageRating again" () {

        when:

        Provider target = Provider.build()

        [0,1,2,3,4,5].each{
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:averageRating target="${target}" />', [target: target]) == "2.5"
    }

    void "test zeroStarCount" () {

        when:

        Provider target = Provider.build()

        [0,1,2,3,4,5].each{
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:zeroStarCount target="${target}" />', [target: target]) == "1"
    }

    void "test zeroStarPct" () {
        when:
        Provider target = Provider.build()
        [0,1,2,3,4].each{
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }
        then:
        assert applyTemplate('<stats:zeroStarPct target="${target}" />', [target: target]) == "20.0"
    }

    void "test oneStarCount" () {

        when:

        Provider target = Provider.build()

        [1,1,1,3,4,5].each{
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:oneStarCount target="${target}" />', [target: target]) == "3"
    }

    void "test oneStarPct" () {
        when:
        Provider target = Provider.build()
        [0,1,2,3,4].each{
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }
        then:
        assert applyTemplate('<stats:oneStarPct target="${target}" />', [target: target]) == "20.0"
    }

    void "test twoStarCount" () {

        when:

        Provider target = Provider.build()

        [2,1,2,2,4,5].each{
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:twoStarCount target="${target}" />', [target: target]) == "3"
    }

    void "test twoStarPct" () {
        when:
        Provider target = Provider.build()
        [0,1,2,3,4].each{
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }
        then:
        assert applyTemplate('<stats:twoStarPct target="${target}" />', [target: target]) == "20.0"
    }

    void "test threeStarCount" () {

        when:

        Provider target = Provider.build()

        [2,1,2,2,4,5].each{
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:threeStarCount target="${target}" />', [target: target]) == "0"
    }

    void "test threeStarPct" () {
        when:
        Provider target = Provider.build()
        [0,1,2,3,4].each{
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }
        then:
        assert applyTemplate('<stats:threeStarPct target="${target}" />', [target: target]) == "20.0"
    }

    void "test fourStarCount" () {

        when:

        Provider target = Provider.build()

        [2,1,2,2,4,5].each{
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:fourStarCount target="${target}" />', [target: target]) == "1"
    }

    void "test fourStarPct" () {
        when:
        Provider target = Provider.build()
        [0,1,2,3,4].each{
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }
        then:
        assert applyTemplate('<stats:fourStarPct target="${target}" />', [target: target]) == "20.0"
    }

    void "test fiveStarCount" () {

        when:

        Provider target = Provider.build()

        [5,5,5,2,5,5,5,5,5].each{
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:fiveStarCount target="${target}" />', [target: target]) == "8"
    }

    void "test fiveStarPct" () {
        when:
        Provider target = Provider.build()
        [1,2,3,4,5].each{
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }
        then:
        assert applyTemplate('<stats:fiveStarPct target="${target}" />', [target: target]) == "20.0"
    }

    void "test reviewSize" () {

        when:

        Provider target = Provider.build()

        [5,5,5,2,5,5,5,5,5].each{
            target.addToReviews([reviewer: Consumer.build(), timestamp: new Date(), rating: it, title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        then:
        assert applyTemplate('<stats:reviewSize target="${target}" />', [target: target]) == "9"
    }

}
