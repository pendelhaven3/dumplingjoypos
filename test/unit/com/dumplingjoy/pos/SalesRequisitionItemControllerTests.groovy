package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(SalesRequisitionItemController)
@Mock(SalesRequisitionItem)
class SalesRequisitionItemControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/salesRequisitionItem/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.salesRequisitionItemInstanceList.size() == 0
        assert model.salesRequisitionItemInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.salesRequisitionItemInstance != null
    }

    void testSave() {
        controller.save()

        assert model.salesRequisitionItemInstance != null
        assert view == '/salesRequisitionItem/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/salesRequisitionItem/show/1'
        assert controller.flash.message != null
        assert SalesRequisitionItem.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/salesRequisitionItem/list'


        populateValidParams(params)
        def salesRequisitionItem = new SalesRequisitionItem(params)

        assert salesRequisitionItem.save() != null

        params.id = salesRequisitionItem.id

        def model = controller.show()

        assert model.salesRequisitionItemInstance == salesRequisitionItem
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/salesRequisitionItem/list'


        populateValidParams(params)
        def salesRequisitionItem = new SalesRequisitionItem(params)

        assert salesRequisitionItem.save() != null

        params.id = salesRequisitionItem.id

        def model = controller.edit()

        assert model.salesRequisitionItemInstance == salesRequisitionItem
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/salesRequisitionItem/list'

        response.reset()


        populateValidParams(params)
        def salesRequisitionItem = new SalesRequisitionItem(params)

        assert salesRequisitionItem.save() != null

        // test invalid parameters in update
        params.id = salesRequisitionItem.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/salesRequisitionItem/edit"
        assert model.salesRequisitionItemInstance != null

        salesRequisitionItem.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/salesRequisitionItem/show/$salesRequisitionItem.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        salesRequisitionItem.clearErrors()

        populateValidParams(params)
        params.id = salesRequisitionItem.id
        params.version = -1
        controller.update()

        assert view == "/salesRequisitionItem/edit"
        assert model.salesRequisitionItemInstance != null
        assert model.salesRequisitionItemInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/salesRequisitionItem/list'

        response.reset()

        populateValidParams(params)
        def salesRequisitionItem = new SalesRequisitionItem(params)

        assert salesRequisitionItem.save() != null
        assert SalesRequisitionItem.count() == 1

        params.id = salesRequisitionItem.id

        controller.delete()

        assert SalesRequisitionItem.count() == 0
        assert SalesRequisitionItem.get(salesRequisitionItem.id) == null
        assert response.redirectedUrl == '/salesRequisitionItem/list'
    }
}
