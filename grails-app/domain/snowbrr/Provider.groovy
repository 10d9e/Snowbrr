package snowbrr

class Provider {

    String companyName

    boolean active = true

    Float initialPrice = 50

    User user

    static hasMany = [reviews: ProviderReview]

    static constraints = {
        user nullable: false
        companyName nullable: false
        initialPrice nullable: false
    }
}
