package desire

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

class DesireController {

    def grailsApplication

    def springSecurityService

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def read() {
        def desire = Desire.findById(params.id.toString())
        render desire as JSON
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def delete() {
        def desire = Desire.findById(params.id.toString())
        desire.status = "deleted"
        desire.save()
    }

    def create(String desireType) {
        if (!params.description.isEmpty()) {
            def user = currentUser
            new Desire(
                            userId: user.id,
                            nickname: user.nickname,
                            description: params.description,
                            createdOn: new Date(),
                            status: "active",
                            type: desireType,
                            geotag: "IF",
                            comments: []).save()
        }
    }


    def update() {
        // we are not going to support editing
        // to avoid situation like:
        // users can agree on something and creator can change the desire completely
        throw new UnsupportedOperationException("Unsupported operation")
    }

    private getCurrentUser() {
        return User.get(springSecurityService.principal.id)
    }
}
