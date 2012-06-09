package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(PaymentTermsController)
@Mock(PaymentTerms)
class PaymentTermsControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/paymentTerms/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.paymentTermsInstanceList.size() == 0
        assert model.paymentTermsInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.paymentTermsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.paymentTermsInstance != null
        assert view == '/paymentTerms/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/paymentTerms/show/1'
        assert controller.flash.message != null
        assert PaymentTerms.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/paymentTerms/list'


        populateValidParams(params)
        def paymentTerms = new PaymentTerms(params)

        assert paymentTerms.save() != null

        params.id = paymentTerms.id

        def model = controller.show()

        assert model.paymentTermsInstance == paymentTerms
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/paymentTerms/list'


        populateValidParams(params)
        def paymentTerms = new PaymentTerms(params)

        assert paymentTerms.save() != null

        params.id = paymentTerms.id

        def model = controller.edit()

        assert model.paymentTermsInstance == paymentTerms
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/paymentTerms/list'

        response.reset()


        populateValidParams(params)
        def paymentTerms = new PaymentTerms(params)

        assert paymentTerms.save() != null

        // test invalid parameters in update
        params.id = paymentTerms.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/paymentTerms/edit"
        assert model.paymentTermsInstance != null

        paymentTerms.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/paymentTerms/show/$paymentTerms.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        paymentTerms.clearErrors()

        populateValidParams(params)
        params.id = paymentTerms.id
        params.version = -1
        controller.update()

        assert view == "/paymentTerms/edit"
        assert model.paymentTermsInstance != null
        assert model.paymentTermsInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/paymentTerms/list'

        response.reset()

        populateValidParams(params)
        def paymentTerms = new PaymentTerms(params)

        assert paymentTerms.save() != null
        assert PaymentTerms.count() == 1

        params.id = paymentTerms.id

        controller.delete()

        assert PaymentTerms.count() == 0
        assert PaymentTerms.get(paymentTerms.id) == null
        assert response.redirectedUrl == '/paymentTerms/list'
    }
}
