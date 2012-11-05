package desire

class UserController {

    def login = {}

    def authenticate = {
        def user = User.findByLoginAndPassword(request.JSON?.login, request.JSON?.password)
        if(user){
            session.user = user
            render(status: 200, text:"Authentication passed!")
        }else{
            render(status: 401, text: "User is not logged in or provided login/password are incorrect!")
        }
    }

    def logout = {
        flash.message = "Goodbye ${session.user?.name}"
        session.user = null
    }
}
