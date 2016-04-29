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

        User admin = new User('admin', 'admin').save()
        UserRole.create admin, adminRole, true

        User jay = new User(username:'jay', password:'jay', firstname: "Jay", lastname: "Logelin", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "jay.logelin@gmail.com", country: "Canada", latitude: 12, longitude: 12).save()
        UserRole.create jay, consumerRole, true
        Consumer consumer = new Consumer(user: jay, driveway: new Driveway(length: 100, width: 75))

        consumer.addToMessages(new Message(fromUsername: "User1",  content: "This is a message", timestamp: new Date()) )
        consumer.addToMessages(new Message(fromUsername: "User2",  content: "This is a message", timestamp: new Date()) )
        consumer.addToMessages(new Message(fromUsername: "User3",  content: "This is a message", timestamp: new Date()) )
        consumer.addToMessages(new Message(fromUsername: "User4",  content: "This is a message", timestamp: new Date()) )
        consumer.addToMessages(new Message(fromUsername: "User5",  content: "This is a message", timestamp: new Date()) )

        save consumer

        User maryse = new User(username:'maryse', password:'maryse', firstname: "Maryse", lastname: "Arseneau", address: "421 Vanier Street", city: "Dieppe",
                province: "NB", email: "maryse.g.arseneau@gmail.com", country: "Canada", latitude: 46.081913, longitude: -64.710136, phone: '506-555-5455').save()
        UserRole.create maryse, providerRole, true
        Provider provider = new Provider(user: maryse, companyName: "Snow Blowers Inc")

        provider.addToMessages(new Message(fromUsername: "User1",  content: "This is a message", timestamp: new Date()) )
        provider.addToMessages(new Message(fromUsername: "User2",  content: "This is a message", timestamp: new Date()) )
        provider.addToMessages(new Message(fromUsername: "User3",  content: "This is a message", timestamp: new Date()) )
        provider.addToMessages(new Message(fromUsername: "User4",  content: "This is a message", timestamp: new Date()) )
        provider.addToMessages(new Message(fromUsername: "User5",  content: "This is a message", timestamp: new Date()) )
        provider.addToReviews( [reviewer: consumer, timestamp: new Date(), rating: 4, content: "Customer was great, cars were moved and customer was very nice."] )

        save provider

        UserRole.create consumer.user, providerRole, true

        consumer.addToReviews( new ConsumerReview( reviewer:provider, timestamp: new Date(), rating: 4, content: "Did a great job, I can see my pavement again!" ) )

        save provider
        save consumer

        15.times {
            Transaction transaction = new Transaction(consumer: consumer, provider: provider, price: 50, status: "in progress", finishBy: new Date(), consumerNotes: "I would like to have this done before the big storm")
            save transaction
        }

        setupProviders(providerRole)
    }

    def setupProviders(def providerRole){

        //46.078731, -64.712379
        User user = new User(username: 'meli', password: 'meli', firstname: "Melisande", lastname: "Logelin", address: "543 Vanier Street", city: "Dieppe",
                    province: "NB", email: "meli.logelin@gmail.com", country: "Canada", latitude: 46.078731, longitude: -64.712379, phone: '506-980-1212').save()
        UserRole.create user, providerRole, true
        Provider provider = new Provider(user: user, companyName: "Mels Snow Blowing")
        provider.addToMessages(new Message(fromUsername: "User1", content: "This is a message", timestamp: new Date()))
        provider.addToMessages(new Message(fromUsername: "User2", content: "This is a message", timestamp: new Date()))
        save provider

        //46.082138, -64.701029
        user = new User(username: 'maddy', password: 'maddy', firstname: "Maddy", lastname: "Logelin", address: "987 Smythe Street", city: "Dieppe",
                province: "NB", email: "maddy.logelin@gmail.com", country: "Canada", latitude: 46.082138, longitude: -64.701029, phone: '506-543-9423').save()
        UserRole.create user, providerRole, true
        provider = new Provider(user: user, companyName: "Maddys Snow Blowing")
        provider.addToMessages(new Message(fromUsername: "User1", content: "This is a message", timestamp: new Date()))
        provider.addToMessages(new Message(fromUsername: "User2", content: "This is a message", timestamp: new Date()))
        save provider

        //46.074281, -64.704897
        user = new User(username: 'jojo', password: 'jojo', firstname: "Jojo", lastname: "Logelin", address: "65 Tranquility Street", city: "Dieppe",
                province: "NB", email: "jojo.logelin@gmail.com", country: "Canada", latitude: 46.074281, longitude: -64.704897, phone: '506-555-6543').save()
        UserRole.create user, providerRole, true
        provider = new Provider(user: user, companyName: "Jojos Snow Blowing")
        provider.addToMessages(new Message(fromUsername: "User1", content: "This is a message", timestamp: new Date()))
        provider.addToMessages(new Message(fromUsername: "User2", content: "This is a message", timestamp: new Date()))
        save provider

        //46.0792963,-64.6955843
        user = new User(username: 'tim', password: 'tim', firstname: "Tim", lastname: "Thompson", address: "77 Aucoin Street", city: "Dieppe",
                province: "NB", email: "timmytim@gmail.com", country: "Canada", latitude: 46.0792963, longitude: -64.6955843, phone: '506-555-7777').save()
        UserRole.create user, providerRole, true
        provider = new Provider(user: user, companyName: "Tim's Snow Removal")
        provider.addToMessages(new Message(fromUsername: "User1", content: "This is a message", timestamp: new Date()))
        provider.addToMessages(new Message(fromUsername: "User2", content: "This is a message", timestamp: new Date()))
        save provider

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
