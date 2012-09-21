package desire

import grails.converters.JSON
import org.bson.types.ObjectId

class DesireController {

    static allowedMethods = [list: "GET", show: "GET", create: "POST", addComment: "POST"]

    def db = MongoService.getDB()
    def ID = 1
    def NICKNAME = "Yuriy"

    def list() {
        def desires = [];
        db.desires.find(status:"active").limit(100).each {
            desires << it
        }
        render new JSON(desires)
    }

    def show() {
        def paramArray = params.id.split("_")
        def desire = db.desires.findOne(_id : new ObjectId(new Date(paramArray[0].toLong()),
                paramArray[1].toInteger()))
        render new JSON(desire)
    }

    def create() {
        if (!request.JSON.description.isEmpty()) {
            db.desires << [userId: ID, nickname: NICKNAME,
                description: request.JSON.description,
                createdOn: new Date(), status: "active",
                type: request.JSON.type,
                geotag: "IF", comments: []]
        }
        list()
    }

    def addComment() {
        def paramArray = request.JSON.id.split("_")
        def desire = db.desires.findOne(_id : new ObjectId(new Date(paramArray[0].toLong()),
                paramArray[1].toInteger()))
        desire.comments << [userId: ID,
                nickname: NICKNAME,
                description: request.JSON.description]
        db.desires.save desire
        render new JSON(desire)
    }
}
