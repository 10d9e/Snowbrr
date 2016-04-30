package snowbrr

class Provider {

    String companyName

    boolean active = true

    User user

    static hasMany = [reviews: ProviderReview]

    static constraints = {
        user nullable: false
        companyName nullable: false
    }
}
