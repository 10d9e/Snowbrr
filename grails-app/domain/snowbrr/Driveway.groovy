package snowbrr

class Driveway {

    byte[] photo

    int length

    int width

    String comment

    static belongsTo = [consumer: Consumer]

    static constraints = {
        length nullable: false
        width nullable: false
        photo nullable: true, maxSize: 1024 * 1024 * 2
        comment nullable: true
    }
}
