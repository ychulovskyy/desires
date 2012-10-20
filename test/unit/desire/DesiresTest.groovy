package desire

import desire.test.functional.pages.DesireListPage
import geb.Browser
import org.junit.Test

/**
 * User: Grygoriy Mykhalyunyo
 * Date: 10/17/12
 * Time: 4:59 PM
 */
class DesiresTest  {

    String getBaseUrl() { "http://localhost:8080/" }

    @Test
    def 'initial page with default data'() {
        Browser.drive {
            to DesireListPage
            assert at(DesireListPage)
        }
    }

}
