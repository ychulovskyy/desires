package desire

class CanController extends DesireController {
    static allowedMethods = [list: "GET", create: "PUT"]
    final desireType = "can"

    def list() {
        list(desireType)
    }

    def create() {
        create(desireType)
    }
}