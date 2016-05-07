package snowbrr

import grails.transaction.Transactional

@Transactional
class StatisticsService {

    def getRatingStatistics(def target) {
        def sum = 0
        def starRatings = [0,0,0,0,0,0]
        Double average = 0
        target.reviews.each {
            sum += it.rating
            starRatings[it.rating]++
        }
        if( target.reviews ) {
            average = sum / target.reviews.size() as Double
            average = average.round(1)
        }
        new RatingStatistics(average: average, starRatings: starRatings, size: target.reviews.size())
    }
}