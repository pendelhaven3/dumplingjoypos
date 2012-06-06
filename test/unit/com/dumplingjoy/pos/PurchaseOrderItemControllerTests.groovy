package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(PurchaseOrderItemController)
@Mock(PurchaseOrderItem)
class PurchaseOrderItemControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/purchaseOrderItem/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.purchaseOrderItemInstanceList.size() == 0
        assert model.purchaseOrderItemInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.purchaseOrderItemInstance != null
    }

    void testSave() {
        controller.save()

        assert model.purchaseOrderItemInstance != null
        assert view == '/purchaseOrderItem/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/purchaseOrderItem/show/1'
        assert controller.flash.message != null
        assert PurchaseOrderItem.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/purchaseOrderItem/list'


        populateValidParams(params)
        def purchaseOrderItem = new PurchaseOrderItem(params)

        assert purchaseOrderItem.save() != null

        params.id = purchaseOrderItem.id

        def model = controller.show()

        assert model.purchaseOrderItemInstance == purchaseOrderItem
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/purchaseOrderItem/list'


        populateValidParams(params)
        def purchaseOrderItem = new PurchaseOrderItem(params)

        assert purchaseOrderItem.save() != null

        params.id = purchaseOrderItem.id

        def model = controller.edit()

        assert model.purchaseOrderItemInstance == purchaseOrderItem
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/purchaseOrderItem/list'

        response.reset()


        populateValidParams(params)
        def purchaseOrderItem = new PurchaseOrderItem(params)

        assert purchaseOrderItem.save() != null

        // test invalid parameters in update
        params.id = purchaseOrderItem.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/purchaseOrderItem/edit"
        assert model.purchaseOrderItemInstance != null

        purchaseOrderItem.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/purchaseOrderItem/show/$purchaseOrderItem.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        purchaseOrderItem.clearErrors()

        populateValidParams(params)
        params.id = purchaseOrderItem.id
        params.version = -1
        controller.update()

        assert view == "/purchaseOrderItem/edit"
        assert model.purchaseOrderItemInstance != null
        assert model.purchaseOrderItemInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/purchaseOrderItem/list'

        response.reset()

        populateValidParams(params)
        def purchaseOrderItem = new PurchaseOrderItem(params)

        assert purchaseOrderItem.save() != null
        assert PurchaseOrderItem.count() == 1

        params.id = purchaseOrderItem.id

        controller.delete()

        assert PurchaseOrderItem.count() == 0
        assert PurchaseOrderItem.get(purchaseOrderItem.id) == null
        assert response.redirectedUrl == '/purchaseOrderItem/list'
    }
}
