package grailswork2

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class UserController {

    static scaffold = true

    def index() { }

    def lockUser(Long id) {
        User.findById(id).accountLocked = !User.findById(id).accountLocked

        redirect action: 'list'
    }
}
