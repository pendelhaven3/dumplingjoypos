package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(SalesRequisitionController)
@Mock(SalesRequisition)
class SalesRequisitionControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/salesRequisition/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.salesRequisitionInstanceList.size() == 0
        assert model.salesRequisitionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.salesRequisitionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.salesRequisitionInstance != null
        assert view == '/salesRequisition/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/salesRequisition/show/1'
        assert controller.flash.message != null
        assert SalesRequisition.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/salesRequisition/list'


        populateValidParams(params)
        def salesRequisition = new SalesRequisition(params)

        assert salesRequisition.save() != null

        params.id = salesRequisition.id

        def model = controller.show()

        assert model.salesRequisitionInstance == salesRequisition
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/salesRequisition/list'


        populateValidParams(params)
        def salesRequisition = new SalesRequisition(params)

        assert salesRequisition.save() != null

        params.id = salesRequisition.id

        def model = controller.edit()

        assert model.salesRequisitionInstance == salesRequisition
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/salesRequisition/list'

        response.reset()


        populateValidParams(params)
        def salesRequisition = new SalesRequisition(params)

        assert salesRequisition.save() != null

        // test invalid parameters in update
        params.id = salesRequisition.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/salesRequisition/edit"
        assert model.salesRequisitionInstance != null

        salesRequisition.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/salesRequisition/show/$salesRequisition.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        salesRequisition.clearErrors()

        populateValidParams(params)
        params.id = salesRequisition.id
        params.version = -1
        controller.update()

        assert view == "/salesRequisition/edit"
        assert model.salesRequisitionInstance != null
        assert model.salesRequisitionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/salesRequisition/list'

        response.reset()

        populateValidParams(params)
        def salesRequisition = new SalesRequisition(params)

        assert salesRequisition.save() != null
        assert SalesRequisition.count() == 1

        params.id = salesRequisition.id

        controller.delete()

        assert SalesRequisition.count() == 0
        assert SalesRequisition.get(salesRequisition.id) == null
        assert response.redirectedUrl == '/salesRequisition/list'
    }
}
