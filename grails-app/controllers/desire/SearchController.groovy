package desire

import grails.converters.JSON
import grails.plugins.springsecurity.Secured


class SearchController {
    def grailsApplication

    def searchService

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def search() {
        render searchService.search(params) as JSON
    }
}
