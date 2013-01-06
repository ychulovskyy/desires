package desire

import grails.plugins.springsecurity.Secured

class CanController extends DesireController {
    static allowedMethods = [list: "GET", create: "PUT"]
    final desireType = "can"

    def springSecurityService

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def list() {
        list(desireType)
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def create() {
        create(desireType)
    }
}