package snowbrr

import grails.transaction.Transactional

@Transactional
class StatisticsService {

    def getAverageProviderRating(Provider provider) {

        int sum = 0

        provider.reviews.each {
            sum += it.rating
        }

        sum / provider.reviews.size()

    }

    def getProviderRatingStatistics(Provider provider) {

        def sum = 0

        def starRatings = [0,0,0,0,0,0]

        provider.reviews.each {
            sum += it.rating
            starRatings[it.rating]++
        }

        Double average = 0
        if( provider.reviews ) {
            average = sum / provider.reviews.size() as Double
            average = average.round(1)
        }

        new ProviderRatingStatistics(average: average, starRatings: starRatings, size: provider.reviews.size())

    }
}