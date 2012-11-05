package desire

class Images {
    String id
    String userId
    byte[] image // avatar or uploaded image for desire
    String moderatedBy

    static constraints = {
        image nullable:false, maxSize: 128*1024
        moderatedBy nullable: true
    }
}
