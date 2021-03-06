package desire

class Desire {
    String id
    String status
    String userId
    String nickname
    String description
    Date createdOn
    String type
    String geotag
    ArrayList<Comment> comments

    static embedded = ['comments']
    static hasMany = [comments:Comment]

    static constraints = {
        description size: 3..500
    }

}
