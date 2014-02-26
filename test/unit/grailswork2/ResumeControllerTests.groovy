package grailswork2



import org.junit.*
import grails.test.mixin.*

@TestFor(ResumeController)
@Mock(Resume)
class ResumeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/resume/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.resumeInstanceList.size() == 0
        assert model.resumeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.resumeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.resumeInstance != null
        assert view == '/resume/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/resume/show/1'
        assert controller.flash.message != null
        assert Resume.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/resume/list'

        populateValidParams(params)
        def resume = new Resume(params)

        assert resume.save() != null

        params.id = resume.id

        def model = controller.show()

        assert model.resumeInstance == resume
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/resume/list'

        populateValidParams(params)
        def resume = new Resume(params)

        assert resume.save() != null

        params.id = resume.id

        def model = controller.edit()

        assert model.resumeInstance == resume
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/resume/list'

        response.reset()

        populateValidParams(params)
        def resume = new Resume(params)

        assert resume.save() != null

        // test invalid parameters in update
        params.id = resume.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/resume/edit"
        assert model.resumeInstance != null

        resume.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/resume/show/$resume.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        resume.clearErrors()

        populateValidParams(params)
        params.id = resume.id
        params.version = -1
        controller.update()

        assert view == "/resume/edit"
        assert model.resumeInstance != null
        assert model.resumeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/resume/list'

        response.reset()

        populateValidParams(params)
        def resume = new Resume(params)

        assert resume.save() != null
        assert Resume.count() == 1

        params.id = resume.id

        controller.delete()

        assert Resume.count() == 0
        assert Resume.get(resume.id) == null
        assert response.redirectedUrl == '/resume/list'
    }
}
