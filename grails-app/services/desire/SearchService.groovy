package desire

class SearchService {
    def grailsApplication

    /**
     * Search for domain objects (e.g. Desire) by params sent in URL.
     * @param URL query params
     * @return list of domain objects
     */
    List search(Map params) {
        // TODO: use factory
        if (["want","can"].contains(params.type?.toString()?.toLowerCase())) {
            return searchForDesires(params);
        } else {
            throw new IllegalArgumentException("Unknown search type: '" + params.type + "'")
        }
    }


    /**
     * Search for Desire by params sent in URL.
     * Example of search string: type=want&theme=technologies&location=703448&pageNo=3&searchFor=java&createdBy=myuser
     * @param URL query params
     * @return list of domain objects
     */
    private List searchForDesires(Map params) {
        def itemsPerPage = grailsApplication.config.desire.desiresOnPage
        def pageNo = Integer.parseInt(params.id?.toString() ?: "0")
        def query = Desire.where{
            status == 'active' && type == params.type
        }
        def desires = query.list(max:itemsPerPage,
                        sort:"createdOn",
                        order:"desc",
                        offset: itemsPerPage * pageNo)
        return desires;
    }
}
