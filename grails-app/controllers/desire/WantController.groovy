package desire

import grails.plugins.springsecurity.Secured

class WantController extends DesireController {
    final desireType = "want"

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def create() {
        create(desireType)
    }
}
