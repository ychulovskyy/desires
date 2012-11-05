package desire

class SecurityFilters {


    def filters = {
        loginCheck(controller: '*', action: '*') {
            before = {
                if (!session.user && actionName != null
                        && !'list'.equals(actionName) && !'show'.equals(actionName)
                        && !'authenticate'.equals(actionName)) {
                    redirect(controller: 'user' , action: "authenticate")
                    return false
                }
            }
        }
    }
}
