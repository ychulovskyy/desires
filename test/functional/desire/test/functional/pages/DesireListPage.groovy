package desire.test.functional.pages

import geb.Page

/**
 * User: Grygoriy Mykhalyunyo
 * Date: 10/17/12
 * Time: 4:24 PM
 */
class DesireListPage extends Page {
    static url = '#/desire'
    static at = { title == "Desire" }

    static content = {
        wantsBlock
        loginForm { $('home-want-block  ') }
    }
}
