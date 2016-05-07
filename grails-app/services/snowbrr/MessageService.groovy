package snowbrr

import grails.transaction.Transactional

@Transactional
class MessageService {

    def send(User from, User to, String content) {
        new Message(from: from, user: to, content: content, timestamp: new Date()).save(flush: true)
    }

}
