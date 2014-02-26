package grailswork2

@grails.validation.Validateable
class UserRegistrationCommand {

    String username
    String password
    String passwordRepeat
    String email
    String phone
    
    static constraints = {
        username size: 4..20
        password size: 4..20,
                validator: {passwd, urc ->
                    return passwd != urc.username
                }
        passwordRepeat nullable: false,
                validator: {passwd2, urc ->
                    return passwd2 == urc.password
                }
        email email: true,
                blank: false
        phone nullable: true,
                size: 6..20
    }
}
