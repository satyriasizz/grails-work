package grailswork2


class RegistrationController {

    def index(UserRegistrationCommand urc) {
        // redirect action: 'registration', params: params
    }

    def registration(UserRegistrationCommand urc) {
        if (urc.hasErrors()) {
            flash.message = "Has errors."
            return [user: urc]
        } else {
            def role = null
            switch (params.type) {
                case 'Worker':
                    role = Role.findByAuthority("ROLE_WORKER")
                    break
                case 'Employer':
                    role = Role.findByAuthority('ROLE_EMPLOYER')
                    break
            }
            if (role == null) {
                flash.message = "undefined type of user"
                return [user: urc]
            }

            def prof = new Profile(email: urc.email, phone: urc.phone)
            def user = new User(username: urc.username, password: urc.password, profile: prof)
            if (user.save()) {
                UserRole.create user, role, true
                render 'Registration successful'
            } else {
                flash.message = "Error while saving"
                redirect action: 'index'
            }
        }
    }


}
