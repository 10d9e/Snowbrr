package snowbrr

import grails.transaction.Transactional

@Transactional
class MessageService {

    def send(User from, User to, String content) {
        new Message(from: from, user: to, content: content, timestamp: new Date()).save(flush: true)
    }

    def sendUpdates(Transaction transaction, String content) {
        new Message(from: transaction.provider, user: transaction.consumer, content: content + "\n\n From: ${transaction.provider.user.username}" , timestamp: new Date()).save(flush: true)
        new Message(from: transaction.consumer, user: transaction.provider, content: content + "\n\n From: ${transaction.provider.user.username}", timestamp: new Date()).save(flush: true)
    }
}
