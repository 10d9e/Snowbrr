package snowbrr

class Consumer {

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

    User user

    static hasMany = [reviews: ConsumerReview, messages: Message, transactions: Transaction]

    static hasOne = [driveway: Driveway]

    static constraints = {
        user nullable: false
        email email: true, nullable: false
        firstname nullable: false
        lastname nullable: false
        address nullable: false
        city nullable: false
        province nullable: false
        country ( nullable: false, inList: ['Canada', 'United States'] )
        avatar(nullable:true, maxSize: 5242880 )
        latitude nullable: false
        longitude nullable: false
    }



}
