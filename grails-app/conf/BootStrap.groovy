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
                province: "NB", email: "maryse.g.arseneau@gmail.com", country: "Canada", latitude: 12, longitude: 12).save()
        UserRole.create maryse, providerRole, true
        Provider provider = new Provider(user: maryse, companyName: "Snow Blowers Inc")

        provider.addToMessages(new Message(fromUsername: "User1",  content: "This is a message", timestamp: new Date()) )
        provider.addToMessages(new Message(fromUsername: "User2",  content: "This is a message", timestamp: new Date()) )
        provider.addToMessages(new Message(fromUsername: "User3",  content: "This is a message", timestamp: new Date()) )
        provider.addToMessages(new Message(fromUsername: "User4",  content: "This is a message", timestamp: new Date()) )
        provider.addToMessages(new Message(fromUsername: "User5",  content: "This is a message", timestamp: new Date()) )

        save provider

        UserRole.create consumer.user, providerRole, true

        consumer.addToReviews( new ConsumerReview( reviewer:provider, timestamp: new Date(), rating: 4, content: "Did a great job, I can see my pavement again!" ) )

        provider.addToReviews( [reviewer: consumer, timestamp: new Date(), rating: 4, content: "Customer was great, cars were moved and customer was very nice."] )
       // provider.addToReviews( new ProviderReview( reviewer: consumer, rating: 4.5, content: "Customer was great, cars were moved and customer was very nice." ) )

        save provider
        save consumer

        15.times {
            Transaction transaction = new Transaction(consumer: consumer, provider: provider, price: 50, status: "in progress", finishBy: new Date(), consumerNotes: "I would like to have this done before the big storm")
            save transaction
        }
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
