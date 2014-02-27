package grailswork2

class Resume {

    String purpose
    String edu
    String exp

    static constraints = {
        purpose minSize: 10
        edu minSize: 10
        exp minSize: 10
    }

    @Override
    String toString() {
        'purpose: ' + purpose + ' edu: ' + edu + ' exp: ' + exp
    }

    @Override
    boolean equals(Object obj) {
        purpose == obj.purpose && edu == obj.edu && exp == obj.exp
    }

    static belongsTo = [profile: Profile]
}
