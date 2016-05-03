import snowbrr.Consumer
import snowbrr.ConsumerReview
import snowbrr.Driveway
import snowbrr.Provider
import snowbrr.Message
import snowbrr.Transaction
import snowbrr.User
import snowbrr.Role
import snowbrr.UserRole

class BootStrap {

    def init = { servletContext ->

        def adminRole = new Role('ROLE_ADMIN').save()
        def consumerRole = new Role('ROLE_CONSUMER').save()
        def providerRole = new Role('ROLE_PROVIDER').save()

        User admin = new User(username:'admin', password:'admin', firstname: "Jay", lastname: "Logelin", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "jay.logelin@gmail.com", country: "Canada", latitude: 12, longitude: 12).save()
        UserRole.create admin, adminRole, true

        setupConsumers(consumerRole)
        setupProviders(providerRole)

        User jay = new User(username:'jay', password:'jay', firstname: "Jay", lastname: "Logelin", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "jay.logelin@gmail.com", country: "Canada", latitude: 12, longitude: 12).save()
        UserRole.create jay, consumerRole, true
        Consumer consumer = new Consumer(user: jay, driveway: new Driveway(length: 100, width: 75))

        //consumer.user.addToMessages(new Message(from: admin, user: consumer.user, content: "This is a message", timestamp: new Date()).save() )
        //consumer.user.addToMessages(new Message(from: admin, user: consumer.user, content: "This is a message", timestamp: new Date()).save() )

        save consumer

        //46.079997, -64.719820

        User maryse = new User(username:'maryse', password:'maryse', firstname: "Maryse", lastname: "Arseneau", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "maryse.g.arseneau@gmail.com", country: "Canada", latitude: 46.079997, longitude: -64.719820, phone: '506-555-5455').save()
        UserRole.create maryse, providerRole, true
        Provider provider = new Provider(user: maryse, companyName: "Snow Blowers Inc")

        //provider.user.addToMessages(new Message(from: jay, user: provider.user, content: "This is a message", timestamp: new Date()) )
        //provider.user.addToMessages(new Message(from: jay,user: provider.user,  content: "This is a message", timestamp: new Date()) )


        Consumer.list().each {
            provider.addToReviews([reviewer: it, timestamp: new Date(), rating: randomInt([0..5]), title: 'Awesome!', content: "Maryse was great, cars were moved and she was very nice."])
        }

        save provider

        //consumer.addToReviews( new ConsumerReview( reviewer:provider, timestamp: new Date(), rating: randomInt([0..5]), title: 'Awesome!', content: "Did a great job, I can see my pavement again!" ) )

        save provider
        save consumer

        2.times {
            Transaction transaction = new Transaction(consumer: consumer, provider: provider, price: 50, status: " Complete", finishBy: new Date(), consumerNotes: "I would like to have this done before the big storm")
            save transaction
        }
    }

    def setupConsumers(def consumerRole) {

        User user = new User(username: 'homer', password: 'homer', firstname: "Homer", lastname: "Simpson", address: "742 Evergreen Terrace", city: "Springfield",
                province: "SK", email: "ChunkyLover53@aol.com", country: "United States", latitude: 46.078731, longitude: -64.712379, phone: '506-555-6832').save()
        Consumer consumer = new Consumer(user: user, driveway: new Driveway(length: 100, width: 75))
        save consumer

        user = new User(username: 'marge', password: 'marge', firstname: "Marge", lastname: "Simpson", address: "742 Evergreen Terrace", city: "Springfield",
                province: "SK", email: "marge@aol.com", country: "United States", latitude: 46.078731, longitude: -64.712379, phone: '506-555-6832').save()
        consumer = new Consumer(user: user, driveway: new Driveway(length: 100, width: 75))
        save consumer

        user = new User(username: 'lisa', password: 'lisa', firstname: "Lisa", lastname: "Simpson", address: "742 Evergreen Terrace", city: "Springfield",
                province: "SK", email: "lisa@aol.com", country: "United States", latitude: 46.078731, longitude: -64.712379, phone: '506-555-6832').save()
        consumer = new Consumer(user: user, driveway: new Driveway(length: 100, width: 75))
        save consumer

        user = new User(username: 'bart', password: 'bart', firstname: "Bart", lastname: "Simpson", address: "742 Evergreen Terrace", city: "Springfield",
                province: "SK", email: "bart@aol.com", country: "United States", latitude: 46.078731, longitude: -64.712379, phone: '506-555-6832').save()
        consumer = new Consumer(user: user, driveway: new Driveway(length: 100, width: 75))
        save consumer

        user = new User(username: 'maggie', password: 'maggie', firstname: "Maggie", lastname: "Simpson", address: "742 Evergreen Terrace", city: "Springfield",
                province: "SK", email: "maggie@aol.com", country: "United States", latitude: 46.078731, longitude: -64.712379, phone: '506-555-6832').save()
        consumer = new Consumer(user: user, driveway: new Driveway(length: 100, width: 75))
        save consumer

    }

    def setupProviders(def providerRole){

        //46.078731, -64.712379
        User user = new User(username: 'meli', password: 'meli', firstname: "Melisande", lastname: "Logelin", address: "543 Vanier Street", city: "Dieppe",
                    province: "NB", email: "meli.logelin@gmail.com", country: "Canada", latitude: 46.078731, longitude: -64.712379, phone: '506-980-1212').save()
        UserRole.create user, providerRole, true
        Provider provider = new Provider(user: user, companyName: "Mels Snow Blowing")
        save provider

        //46.082138, -64.701029
        user = new User(username: 'maddy', password: 'maddy', firstname: "Maddy", lastname: "Logelin", address: "987 Smythe Street", city: "Dieppe",
                province: "NB", email: "maddy.logelin@gmail.com", country: "Canada", latitude: 46.082138, longitude: -64.701029, phone: '506-543-9423').save()
        UserRole.create user, providerRole, true
        provider = new Provider(user: user, companyName: "Maddys Snow Blowing")
        save provider

        //46.074281, -64.704897
        user = new User(username: 'jojo', password: 'jojo', firstname: "Jojo", lastname: "Logelin", address: "65 Tranquility Street", city: "Dieppe",
                province: "NB", email: "jojo.logelin@gmail.com", country: "Canada", latitude: 46.074281, longitude: -64.704897, phone: '506-555-6543').save()
        UserRole.create user, providerRole, true
        provider = new Provider(user: user, companyName: "Jojos Snow Blowing")
        save provider

        //46.0792963,-64.6955843
        user = new User(username: 'tim', password: 'tim', firstname: "Tim", lastname: "Thompson", address: "77 Aucoin Street", city: "Dieppe",
                province: "NB", email: "timmytim@gmail.com", country: "Canada", latitude: 46.0792963, longitude: -64.6955843, phone: '506-555-7777').save()
        UserRole.create user, providerRole, true
        provider = new Provider(user: user, companyName: "Tim's Snow Removal")
        save provider

    }

    static def r = new Random()

    private static int randomInt(int max){
        r.nextInt(max)
    }

    private static int randomInt(Range<Integer> range) {
        range.from + r.nextInt(range.to - range.from + 1)
    }


    private static Date randomDate(Range<Date> range) {
        range.from + r.nextInt(range.to - range.from + 1)
    }

    def destroy = {
    }

    def save(def o){
        if(!o.validate()){
            o.errors.allErrors.each {println it}
        }else {
            o.save(flush: true)
        }
    }
}
