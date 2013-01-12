package desire

class Comment {
    String commentId
    String userId
    String nickname
    String description
    String updatedBy

    static constraints = {
        description size: 3..500
        updatedBy nullable: true
    }
}
