package desire

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

class DesireController {

    static allowedMethods = [show: "GET"]

    def grailsApplication

    def springSecurityService

    def list(String desireType) {
        def pageNo = Integer.parseInt(params.id?.toString() ?: "0")
        def itemsPerPage = grailsApplication.config.desire.desiresOnPage

        def desires = Desire.findAllByStatusAndType('active',
                desireType,
                [max:itemsPerPage,
                        sort:"createdOn",
                        order:"desc",
                        offset: itemsPerPage * pageNo])
        render desires as JSON
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def show() {
        def desire = Desire.findById(params.id.toString())
        render desire as JSON
    }

    def create(String desireType) {
        def submittedDesire = request.JSON
        if (!submittedDesire.description.isEmpty()) {
            def user = currentUser
            new Desire(
                            userId: user.id,
                            nickname: user.nickname,
                            description: submittedDesire.description,
                            createdOn: new Date(),
                            status: "active",
                            type: desireType,
                            geotag: "IF",
                            comments: []).save()
        }
        list()
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
