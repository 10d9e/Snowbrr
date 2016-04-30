package snowbrr

class ConsumerReview {

    Provider reviewer

    Date timestamp

    int rating

    String title

    String content

    static belongsTo = [consumer: Consumer]

    static constraints = {
        timestamp nullable: false
        rating range: 0..5, nullable: false
        reviewer unique: true
        content nullable: true
        title nullable: true
    }
}
