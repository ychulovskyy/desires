package desire

import grails.plugins.springsecurity.Secured

class CanController extends DesireController {
    final desireType = "can"

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def create() {
        create(desireType)
    }
}