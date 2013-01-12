package desire

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.bson.types.ObjectId

class CommentController {

    static allowedMethods = [create: "PUT", delete: "DELETE"]

    def grailsApplication

    def springSecurityService

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def create() {
        def desire = Desire.findById(params.desireid.toString())
        def user = currentUser
        desire.comments << new Comment(
                commentId: new ObjectId().toString(),
                userId: user.id,
                nickname: user.nickname,
                description: request.JSON.description)
        desire.save()
        render desire as JSON
    }

    //@Secured(['IS_AUTHENTICATED_FULLY'])
    def delete() {
        def desire = Desire.findById(params.desireid.toString())
        Comment comment = null
        desire.comments.each {
            if (params.id.equals(it.commentId)) {
                comment = it
            }
        }
        desire.removeFromComments(comment)
        desire.save()
        render desire as JSON
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def update() {
//        def desire = Desire.findById(params.desireid.toString())
//        def user = currentUser
//        def comment = desire.comments.findById(params.id)
//        comment.description = request.JSON.description
//        comment.updatedBy = user
//        comment.save()
//        desire.save()
//        render desire as JSON
    }

    private getCurrentUser() {
        return User.get(springSecurityService.principal.id)
    }
}
