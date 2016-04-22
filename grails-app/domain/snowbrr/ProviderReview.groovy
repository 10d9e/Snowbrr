package snowbrr

class ProviderReview {

    Consumer reviewer

    Date timestamp

    int rating

    String content

    static belongsTo = [provider: Provider]

    static constraints = {
        timestamp nullable: false
        rating range: 0..5, nullable: false
        reviewer unique: true
        content nullable: true
    }
}
