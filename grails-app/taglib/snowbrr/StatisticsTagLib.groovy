package snowbrr

class StatisticsTagLib {

    static namespace = "stats"

    def statisticsService

    /**
     * @attr target REQUIRED
     */
    def renderStars = { attrs ->

        RatingStatistics stats = statisticsService.getRatingStatistics(attrs.target)

        def average = stats.average.trunc()

        average.times {
            out << """<button type="button" class="btn btn-warning btn-sm" aria-label="Left Align">
                        <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                      </button>"""
        }

        5.minus(average).times {

            out << """ <button type="button" class="btn btn-default btn-grey btn-sm" aria-label="Left Align">
                         <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                       </button>"""
        }
    }

    /**
     * @attr target REQUIRED
     */
    def averageRating = { attrs ->
        out << statisticsService.getRatingStatistics(attrs.target).average
    }

    /**
     * @attr target REQUIRED
     */
    def zeroStarCount = { attrs ->
        out << statisticsService.getRatingStatistics(attrs.target).starRatings[0]
    }

    /**
     * @attr target REQUIRED
     */
    def zeroStarPct = { attrs ->
        processStarPct attrs.target, 0, out
    }

    /**
     * @attr target REQUIRED
     */
    def oneStarCount = { attrs ->
        out << statisticsService.getRatingStatistics(attrs.target).starRatings[1]
    }

    /**
     * @attr target REQUIRED
     */
    def oneStarPct = { attrs ->
        processStarPct attrs.target, 1, out
    }

    /**
     * @attr target REQUIRED
     */
    def twoStarCount = { attrs ->
        out << statisticsService.getRatingStatistics(attrs.target).starRatings[2]
    }

    /**
     * @attr target REQUIRED
     */
    def twoStarPct = { attrs ->
        processStarPct attrs.target, 2, out
    }

    /**
     * @attr target REQUIRED
     */
    def threeStarCount = { attrs ->
        out << statisticsService.getRatingStatistics(attrs.target).starRatings[3]
    }

    /**
     * @attr target REQUIRED
     */
    def threeStarPct = { attrs ->
        processStarPct attrs.target, 3, out
    }

    /**
     * @attr target REQUIRED
     */
    def fourStarCount = { attrs ->
        out << statisticsService.getRatingStatistics(attrs.target).starRatings[4]
    }

    /**
     * @attr target REQUIRED
     */
    def fourStarPct = { attrs ->
        processStarPct attrs.target, 4, out
    }

    /**
     * @attr target REQUIRED
     */
    def fiveStarCount = { attrs ->
        out << statisticsService.getRatingStatistics(attrs.target).starRatings[5]
    }

    /**
     * @attr target REQUIRED
     */
    def fiveStarPct = { attrs ->
        processStarPct attrs.target, 5, out
    }

    def processStarPct(def target, int starRating, def out){
        RatingStatistics stats = statisticsService.getRatingStatistics(target)
        def v = stats.size > 0 ? stats.starRatings[starRating] / stats.size * 100 : 0
        out << v
    }

    /**
     * @attr target REQUIRED
     */
    def reviewSize = { attrs ->
        out << statisticsService.getRatingStatistics(attrs.target).size
    }
}