package desire

import grails.converters.JSON

class DesireController {

    static allowedMethods = [show: "GET", addComment: "PUT"]

    def static ID = 1
    def static NICKNAME = "Yuriy"

    def grailsApplication

    def list(String desireType) {
        def pageNo = Integer.parseInt(params.id?.toString() ?: "0")
        def itemsPerPage = grailsApplication.config.desire.desiresOnPage

        def desires = Desire.findAllByStatusAndType('active',
                desireType,
                [max:itemsPerPage,
                        sort:"createdOn",
                        order:"desc",
                        offset: itemsPerPage * pageNo])
        render desires as JSON
    }

    def show() {
        def desire = Desire.findById(params.id.toString())
        render desire as JSON
    }

    def create(String desireType) {
        def submittedDesire = request.JSON
        if (!submittedDesire.description.isEmpty()) {
            new Desire(
                            userId: ID,
                            nickname: NICKNAME,
                            description: submittedDesire.description,
                            createdOn: new Date(),
                            status: "active",
                            type: desireType,
                            geotag: "IF",
                            comments: []).save()
        }
        list()
    }

    def addComment() {
        def desire = Desire.findById(request.JSON.id.toString())
        desire.comments << new Comment(userId: ID, nickname: NICKNAME, description: request.JSON.description)
        desire.save()
        render desire as JSON
    }
}
