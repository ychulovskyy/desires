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
    ArrayList<Comment> comments

    static embedded = ['comments']
    static hasMany = [comments:Comment]

}
