package desire

import org.bson.types.ObjectId

class Comment {
    ObjectId id
    Long userId
    String nickname
    String description

    static constraints = {
    }

    static mapWith = "mongo"

}
