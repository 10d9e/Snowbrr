package snowbrr

class Transaction {

    double price = 50

    String status = 'Request'

    byte [] photoProof

    Date finishBy

    String providerNotes

    String consumerNotes

    static belongsTo = [provider: Provider, consumer: Consumer]

    static constraints = {
        status inList: ['Request', 'Cancel', 'Price Change', 'Not Started', 'In Progress', 'Complete'], nullable: false
        finishBy (nullable: false, min: new Date() )
        photoProof nullable: true
        providerNotes nullable: true
        consumerNotes nullable: true
        provider nullable: true
        consumer nullable: true
    }
}
