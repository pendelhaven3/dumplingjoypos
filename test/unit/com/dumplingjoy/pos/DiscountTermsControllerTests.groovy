package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(DiscountTermsController)
@Mock(DiscountTerms)
class DiscountTermsControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/discountTerms/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.discountTermsInstanceList.size() == 0
        assert model.discountTermsInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.discountTermsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.discountTermsInstance != null
        assert view == '/discountTerms/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/discountTerms/show/1'
        assert controller.flash.message != null
        assert DiscountTerms.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/discountTerms/list'


        populateValidParams(params)
        def discountTerms = new DiscountTerms(params)

        assert discountTerms.save() != null

        params.id = discountTerms.id

        def model = controller.show()

        assert model.discountTermsInstance == discountTerms
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/discountTerms/list'


        populateValidParams(params)
        def discountTerms = new DiscountTerms(params)

        assert discountTerms.save() != null

        params.id = discountTerms.id

        def model = controller.edit()

        assert model.discountTermsInstance == discountTerms
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/discountTerms/list'

        response.reset()


        populateValidParams(params)
        def discountTerms = new DiscountTerms(params)

        assert discountTerms.save() != null

        // test invalid parameters in update
        params.id = discountTerms.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/discountTerms/edit"
        assert model.discountTermsInstance != null

        discountTerms.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/discountTerms/show/$discountTerms.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        discountTerms.clearErrors()

        populateValidParams(params)
        params.id = discountTerms.id
        params.version = -1
        controller.update()

        assert view == "/discountTerms/edit"
        assert model.discountTermsInstance != null
        assert model.discountTermsInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/discountTerms/list'

        response.reset()

        populateValidParams(params)
        def discountTerms = new DiscountTerms(params)

        assert discountTerms.save() != null
        assert DiscountTerms.count() == 1

        params.id = discountTerms.id

        controller.delete()

        assert DiscountTerms.count() == 0
        assert DiscountTerms.get(discountTerms.id) == null
        assert response.redirectedUrl == '/discountTerms/list'
    }
}
