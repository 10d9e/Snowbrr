package snowbrr

class Provider {

    String companyName

    boolean active = true

    User user

    static hasMany = [reviews: ProviderReview, messages: Message, transactions: Transaction]

    static constraints = {
        user nullable: false
        companyName nullable: false
    }
}
