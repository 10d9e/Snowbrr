package snowbrr

class StatisticsTagLib {

    static namespace = "stats"

    def statisticsService

    /**
     * @attr provider REQUIRED
     */
    def renderStars = { attrs ->

        ProviderRatingStatistics stats = statisticsService.getProviderRatingStatistics(attrs.provider)

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
     * @attr provider REQUIRED
     */
    def averageRating = { attrs ->
        out << statisticsService.getProviderRatingStatistics(attrs.provider).average
    }

    /**
     * @attr provider REQUIRED
     */
    def zeroStarCount = { attrs ->
        out << statisticsService.getProviderRatingStatistics(attrs.provider).starRatings[0]
    }

    /**
     * @attr provider REQUIRED
     */
    def zeroStarPct = { attrs ->
        processStarPct attrs.provider, 0, out
    }

    /**
     * @attr provider REQUIRED
     */
    def oneStarCount = { attrs ->
        out << statisticsService.getProviderRatingStatistics(attrs.provider).starRatings[1]
    }

    /**
     * @attr provider REQUIRED
     */
    def oneStarPct = { attrs ->
        processStarPct attrs.provider, 1, out
    }

    /**
     * @attr provider REQUIRED
     */
    def twoStarCount = { attrs ->
        out << statisticsService.getProviderRatingStatistics(attrs.provider).starRatings[2]
    }

    /**
     * @attr provider REQUIRED
     */
    def twoStarPct = { attrs ->
        processStarPct attrs.provider, 2, out
    }

    /**
     * @attr provider REQUIRED
     */
    def threeStarCount = { attrs ->
        out << statisticsService.getProviderRatingStatistics(attrs.provider).starRatings[3]
    }

    /**
     * @attr provider REQUIRED
     */
    def threeStarPct = { attrs ->
        processStarPct attrs.provider, 3, out
    }

    /**
     * @attr provider REQUIRED
     */
    def fourStarCount = { attrs ->
        out << statisticsService.getProviderRatingStatistics(attrs.provider).starRatings[4]
    }

    /**
     * @attr provider REQUIRED
     */
    def fourStarPct = { attrs ->
        processStarPct attrs.provider, 4, out
    }

    /**
     * @attr provider REQUIRED
     */
    def fiveStarCount = { attrs ->
        out << statisticsService.getProviderRatingStatistics(attrs.provider).starRatings[5]
    }

    /**
     * @attr provider REQUIRED
     */
    def fiveStarPct = { attrs ->
        processStarPct attrs.provider, 5, out
    }

    def processStarPct(def provider, int starRating, def out){
        ProviderRatingStatistics stats = statisticsService.getProviderRatingStatistics(provider)
        def v = stats.size > 0 ? stats.starRatings[starRating] / stats.size * 100 : 0
        out << v
    }

    /**
     * @attr provider REQUIRED
     */
    def reviewSize = { attrs ->
        out << statisticsService.getProviderRatingStatistics(attrs.provider).size
    }
}