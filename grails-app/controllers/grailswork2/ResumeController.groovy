package grailswork2

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_ADMIN', 'ROLE_WORKER'])
class ResumeController {

    static allowedMethods = [save: "POST", update: "POST", delete: ["GET", "POST"]] // Get needed for Worker and Post was generated

    def index() {
        redirect(action: "list", params: params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EMPLOYER'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [resumeInstanceList: Resume.list(params), resumeInstanceTotal: Resume.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EMPLOYER'])
    def mailWorker(Long id) {
        [email: Profile.findById(id).email]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EMPLOYER'])
    def sendEmail() {
        try {
            sendMail {
                to params.email
                subject params.subject
                body params.subject
            }
        } catch (ConnectException e) {
            log.error("Connection refused with mail: ${params.email}", e)
            redirect action: 'list'
        }
    }

    def create() {
        if (getAuthenticatedUser().profile.resume != null) {
            redirect uri: ''
        }
        [resumeInstance: new Resume(params)]
    }

    def save() {
        def resumeInstance = new Resume(params)

        //***
        User user = getAuthenticatedUser()
        resumeInstance.setProfile(user.profile)
        //***

        if (!resumeInstance.save(flush: true)) {
            render(view: "create", model: [resumeInstance: resumeInstance])
            return
        }

        //------- Add resume to authenticated user, add profile-link to resume
        user.profile.setResume(resumeInstance)
        println user.profile.resume
        user.save()
        //-------

        flash.message = message(code: 'default.created.message', args: [message(code: 'resume.label', default: 'Resume'), resumeInstance.id])

        redirect(action: "show", id: resumeInstance.id)
    }

    def show(Long id) {
        def resumeInstance = Resume.get(id)
        if (!resumeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'resume.label', default: 'Resume'), id])
            redirect(action: "list")
            return
        }

        [resumeInstance: resumeInstance]
    }

    def edit(Long id) {
        //----------
        if (id == null) {
            User user = getAuthenticatedUser()
            id = user.profile.resume?.id
            if (id == null) {
                redirect uri: ''
                return
            }
        }
        //---------
        def resumeInstance = Resume.get(id)
        if (!resumeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'resume.label', default: 'Resume'), id])
            redirect(action: "list")
            return
        }

        [resumeInstance: resumeInstance]
    }

    def update(Long id, Long version) {
        def resumeInstance = Resume.get(id)
        if (!resumeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'resume.label', default: 'Resume'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (resumeInstance.version > version) {
                resumeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'resume.label', default: 'Resume')] as Object[],
                        "Another user has updated this Resume while you were editing")
                render(view: "edit", model: [resumeInstance: resumeInstance])
                return
            }
        }

        resumeInstance.properties = params

        if (!resumeInstance.save(flush: true)) {
            render(view: "edit", model: [resumeInstance: resumeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'resume.label', default: 'Resume'), resumeInstance.id])
        redirect(action: "show", id: resumeInstance.id)
    }

    def delete(Long id) {

        redirect uri: ''
        if (id == null ) {
            User u = getAuthenticatedUser()
            id = u.profile.resume?.id
            u.profile.resume = null

            if(id == null) { //resume already deleted

                return
            }
        }



        def resumeInstance = Resume.get(id)
        if (!resumeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'resume.label', default: 'Resume'), id])
            return
        }

        try {
            resumeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'resume.label', default: 'Resume'), id])
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'resume.label', default: 'Resume'), id])
        }
    }


    def searchSame() {
        User user = getAuthenticatedUser()
        def resume = user.profile.resume

        println user
        println resume

        HashSet result = Resume.findAllByPurposeOrEduLikeOrExp(resume.purpose, resume.edu, resume.exp);
        result.remove(resume)

        [result: result]
    }
}
