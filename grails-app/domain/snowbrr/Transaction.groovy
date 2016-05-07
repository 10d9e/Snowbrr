package snowbrr

class Transaction implements Comparable<Transaction>{

    double price = 50

    String status = 'Request'

    byte [] photoProof

    Date finishBy

    String providerNotes

    String consumerNotes

    static belongsTo = [provider: Provider, consumer: Consumer]

    static constraints = {
        status inList: ['Request', 'Cancelled', 'Price Change', 'In Progress', 'Complete'], nullable: false
        finishBy (nullable: false )
        photoProof nullable: true, maxSize: 10485760
        providerNotes nullable: true
        consumerNotes nullable: true
        provider nullable: true
        consumer nullable: true
    }

    int compareTo(Transaction other){
        this.finishBy? other?.finishBy.compareTo(this?.finishBy) : -1
    }

}
