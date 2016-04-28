package snowbrr

class Consumer {

    User user

    static hasMany = [reviews: ConsumerReview, messages: Message, transactions: Transaction]

    static hasOne = [driveway: Driveway]

    static constraints = {
        user nullable: false

    }
}
