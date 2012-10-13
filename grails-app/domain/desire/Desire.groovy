package desire

import org.bson.types.ObjectId

class Desire {
    ObjectId id
    String status
    Long userId
    String nickname
    String description
    Date createdOn
    String type
    String geotag
    List<Comment> comments

    static embedded = ['comments']
    static mapWith = "mongo"
    static hasMany = [comments:Comment]

}
