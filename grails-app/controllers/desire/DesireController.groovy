package desire

import grails.converters.JSON
import org.bson.types.ObjectId

class DesireController {

    static allowedMethods = [list: "GET", show: "GET", create: "POST", addComment: "POST"]

    def static ID = 1
    def static NICKNAME = "Yuriy"

    def grailsApplication

    def list() {
        def desires = Desire.findAllByStatus('active', [max:grailsApplication.config.desire.desiresOnPage])
        render new JSON(desires)
    }

    def show() {
        def paramArray = params.id.toString().split('_')
        def desire = Desire.findById(new ObjectId(new Date(paramArray[0].toLong()), paramArray[1].toInteger()))
        render new JSON(desire)
    }

    def create() {
        def submittedDesire = request.JSON
        if (!submittedDesire.description.isEmpty()) {
            new Desire(
                            userId: 1,
                            nickname: NICKNAME,
                            description: submittedDesire.description,
                            createdOn: new Date(),
                            status: "active",
                            type: submittedDesire.type,
                            geotag: "IF",
                            comments: []).save()
        }
        list()
    }

    def addComment() {
        def paramArray = request.JSON.id.toString().split("_")
        def desire = Desire.findById(new ObjectId(new Date(paramArray[0].toLong()), paramArray[1].toInteger()))
        desire.comments << new Comment(userId: ID, nickname: NICKNAME, description: request.JSON.description)
        desire.save()
        render new JSON(desire)
    }
}
