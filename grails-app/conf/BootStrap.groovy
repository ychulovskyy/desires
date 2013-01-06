import desire.Comment
import desire.Desire
import desire.SecRole
import desire.SecUser
import desire.SecUserSecRole
import desire.User

class BootStrap {

    def springSecurityService

    def init = { servletContext ->
        initTestData()
    }

    private void initTestData() {
        def userRole = getOrCreateRole("ROLE_USER")
        def adminRole = getOrCreateRole("ROLE_ADMIN")

        if (!User.count()) {
            // Start with the admin user.
            def adminUser = new User(
                    username: "admin",
                    nickname: "Chuck Norris",
                    password: "admin",
                    enabled: true,
                    status : "active").save(failOnError :true)
            SecUserSecRole.create adminUser, adminRole

            // Now the normal user.
            def user = new User(
                    username: "user",
                    nickname: "simple user",
                    password: "user",
                    enabled: true,
                    status : "active").save(failOnError:true)
            SecUserSecRole.create user, userRole
        }
        if (!Desire.count()) {
            def user = User.findAll().get(0);

            new Desire(userId: user.id , nickname: user.nickname, description: "Learn Groovy",
                    createdOn: new Date(), status: "active",
                    type: "want", geotag: "IF",
                    comments: [new Comment(userId: 3, nickname: "Kolya", description: "Just do it dude")]).save(failOnError:true)

            new Desire(userId: user.id , nickname: user.nickname, description: "Teach Java", createdOn: new Date(), status: "active",
                    type: "can", geotag: "IF", comments: []).save(failOnError:true)

            new Desire(userId: user.id , nickname: user.nickname, description: "Find a dude to visit New Zeland togather",
                    createdOn: new Date(), status: "active",
                    type: "want", geotag: "IF",
                    comments: []).save(failOnError:true)
        }
    }

    private getOrCreateRole(name) {
        def role = SecRole.findByAuthority(name)
        if (!role) role = new SecRole(authority: name).save()
        if (!role) println "Unable to save role ${name}"
        return role
    }

    def destroy = {
    }
}
