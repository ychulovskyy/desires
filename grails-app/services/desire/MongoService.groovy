package desire

import com.gmongo.GMongo

@Grab(group = 'com.gmongo', module = 'gmongo', version = '0.9.3')
class MongoService {
    private static def db = null;

    def static getDB() {
        if (db == null) {
            // Instantiate a com.gmongo.GMongo object
            def mongo = new GMongo("127.0.0.1", 27017)

            // Get a db reference in the old fashion way
            db = mongo.getDB("desires")

            prepareDemoData(db)
        }
        return db
    }

    def static prepareDemoData(db) {
        // drop DB
        db.desires.drop()
        // insert a few values for demo
        db.desires << [userId: 1, nickname: "Yuriy", description: "Learn Groovy",
                createdOn: new Date(), status: "active",
                type: "want", geotag: "IF",
                comments: [[userId: 3, nickname: "Kolya", description: "Just do it dude"]]];
        db.desires << [userId: 1, name: "Yuriy", description: "Teach Java",
                createdOn: new Date(), status: "active",
                type: "can", geotag: "IF",
                comments: []]
        db.desires << [userId: 2, name: "Yuriy", description: "Find a dude to visit New Zeland togather",
                createdOn: new Date(), status: "active",
                type: "want", geotag: "IF",
                comments: []]
    }
}
