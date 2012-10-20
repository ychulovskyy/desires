import desire.Comment
import desire.Desire
import org.bson.types.ObjectId
import org.apache.commons.lang.StringUtils

class BootStrap {

    def init = { servletContext ->
        initTestData()
    }

    private void initTestData() {
        if (!Desire.count()) {
            new Desire(userId: 1, nickname: "Yuriy", description: "Learn Groovy",
                    createdOn: new Date(), status: "active",
                    type: "want", geotag: "IF",
                    comments: [new Comment(userId: 3, nickname: "Kolya", description: "Just do it dude")]).save()

            new Desire(userId: 1, nickname: "Yuriy", description: "Teach Java", createdOn: new Date(), status: "active",
                    type: "can", geotag: "IF", comments: []).save()

            new Desire(userId: 1, nickname: "Yuriy", description: "Find a dude to visit New Zeland togather",
                    createdOn: new Date(), status: "active",
                    type: "want", geotag: "IF",
                    comments: []).save()
        }
    }

    def destroy = {
    }
}
