package desire

class User extends SecUser {
    String email
    String nickname
    String description
    String avatarId // id of image
    String status

    static constraints = {
        email unique: true, nullable: true
        description nullable: true
        avatarId nullable:true
        status inList:["active", "blocked", "deleted"]
    }
}
