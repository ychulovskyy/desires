package desire

class WantController extends DesireController {
    static allowedMethods = [list: "GET", create: "PUT"]
    final desireType = "want"

    def list() {
        list(desireType)
    }

    def create() {
        create(desireType)
    }
}
