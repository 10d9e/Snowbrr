package snowbrr

class Message {

    String content

    String fromUsername

    boolean read = false

    Date timestamp

    static constraints = {
        timestamp nullable: false
        fromUsername nullable: false
    }
}
