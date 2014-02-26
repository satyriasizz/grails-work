package grailswork2

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_EMPLOYER'])
class EmployerController {

    def index() {
        redirect action: employers()
    }

    def employers() {
        [resumes: Resume.all]
    }
}
