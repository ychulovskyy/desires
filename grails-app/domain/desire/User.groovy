package desire

class User {
    String id
    String login
    String password
    String email
    String nickname
    String description
    String avatarId // id of image
    String role
    String status

    static constraints = {
        login unique: true
        email unique: true
        password password:true
        status inList:["active", "blocked", "deleted"]
        role inList:["user", "moderator", "admin"]
        avatarId nullable:true
    }
}
