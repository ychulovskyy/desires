package desire

class Comment {
    String id
    Long userId
    String nickname
    String description

    static constraints = {
        description size: 3..500
    }
}
