package snowbrr

class Consumer {

    User user

    static hasMany = [reviews: ConsumerReview]

    static hasOne = [driveway: Driveway]

    static constraints = {
        user nullable: false
    }
}
