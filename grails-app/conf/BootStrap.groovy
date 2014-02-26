import grailswork2.Resume
import grailswork2.Role
import grailswork2.User
import grailswork2.Profile
import grailswork2.UserRole

class BootStrap {

    def init = { servletContext ->

//        def admn = new Profile(email: "drLitvinenko@gmail.com",
//                phone: "0671422488",
//                name: "Andrey",
//                pass: "1111").save()

        def resume = new Resume(exp: 'IT STEP 2008-2010', purpose: 'Java develop', edu: 'NMU FIT 2008-2013')
        def resume1 = new Resume(exp: 'IT STEP 2008-2010', purpose: 'Java develop', edu: 'NMU FIT 2008-2013')
        def prof = new Profile(email: 'drLitvinenko@gmail.com', phone: '0671422488', resume: resume)
        def prof1 = new Profile(email: 'saty@gmail.com', phone: '0671422488', resume: resume1)
        def test = new User(username: 'admn', password: '1111', profile: prof)
        def test1 = new User(username: 'saty', password: '1111', profile: prof)
        def test2 = new User(username: 'pche', password: '1111', profile: prof1)
        def test3 = new User(username: 'night', password: '1111', profile: prof)
        def test4 = new User(username: 'andrey', password: '1111', profile: prof)

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def workerRole = new Role(authority: 'ROLE_WORKER').save(flush: true)
        def employerRole = new Role(authority: 'ROLE_EMPLOYER').save(flush: true)

        test.save(flush: true)
        test1.save(flush: true)
        test2.save(flush: true)
        test3.save(flush: true)
        test4.save(flush: true)

        UserRole.create test, adminRole, true
        UserRole.create test1, employerRole, true
        UserRole.create test2, workerRole, true
        UserRole.create test3, workerRole, true
        UserRole.create test4, workerRole, true

        assert User.count() == 5
        assert Role.count() == 3
    }

    def destroy = {
    }
}
