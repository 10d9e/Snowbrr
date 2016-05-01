package snowbrr

class Message implements Comparable<Message>{

    String content

    User from

    boolean read = false

    Date timestamp

    static belongsTo = [user: User]

    static constraints = {
        timestamp nullable: false
        from nullable: false
    }

    int compareTo(Message other){
        timestamp.compareTo(other.timestamp)
    }

}
