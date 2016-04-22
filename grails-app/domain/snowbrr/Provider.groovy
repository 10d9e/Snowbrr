package snowbrr

class Provider {

    String email

    String firstname

    String lastname

    String address

    String city

    String province

    String country

    double latitude

    double longitude

    byte[] avatar

    String companyName

    boolean active = false

    User user

    static hasMany = [reviews: ProviderReview, messages: Message, transactions: Transaction]

    static constraints = {
        user nullable: false
        email email: true
        firstname nullable: false
        lastname nullable: false
        address nullable: false
        city nullable: false
        province nullable: false
        country ( nullable: false, inList: ['Canada', 'United States'] )
        avatar(nullable:true, maxSize: 5242880 )
        companyName nullable: false
    }

}
