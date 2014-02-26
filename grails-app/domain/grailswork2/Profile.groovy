package grailswork2

class Profile {

    String phone
    String email
    Resume resume

    static constraints = {
        phone blank: false
        email email: true, unique: true
        resume nullable: true
    }

    @Override
    String toString() {
        "Resume: $resume"
    }

    static belongsTo = User
}
