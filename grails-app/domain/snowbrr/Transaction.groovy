package snowbrr

class Transaction {

    double price

    String status = 'request'

    byte [] photoProof

    Date finishBy

    String providerNotes

    String consumerNotes

    static belongsTo = [provider: Provider, consumer: Consumer]

    static constraints = {
        status inList: ['request', 'not started', 'in progress', 'complete'], nullable: false
        finishBy (nullable: false, min: new Date() )
        photoProof nullable: true
        providerNotes nullable: true
        consumerNotes nullable: true
        provider nullable: true
    }
}
