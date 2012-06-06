package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(ReceivingReceiptItemController)
@Mock(ReceivingReceiptItem)
class ReceivingReceiptItemControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/receivingReceiptItem/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.receivingReceiptItemInstanceList.size() == 0
        assert model.receivingReceiptItemInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.receivingReceiptItemInstance != null
    }

    void testSave() {
        controller.save()

        assert model.receivingReceiptItemInstance != null
        assert view == '/receivingReceiptItem/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/receivingReceiptItem/show/1'
        assert controller.flash.message != null
        assert ReceivingReceiptItem.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/receivingReceiptItem/list'


        populateValidParams(params)
        def receivingReceiptItem = new ReceivingReceiptItem(params)

        assert receivingReceiptItem.save() != null

        params.id = receivingReceiptItem.id

        def model = controller.show()

        assert model.receivingReceiptItemInstance == receivingReceiptItem
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/receivingReceiptItem/list'


        populateValidParams(params)
        def receivingReceiptItem = new ReceivingReceiptItem(params)

        assert receivingReceiptItem.save() != null

        params.id = receivingReceiptItem.id

        def model = controller.edit()

        assert model.receivingReceiptItemInstance == receivingReceiptItem
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/receivingReceiptItem/list'

        response.reset()


        populateValidParams(params)
        def receivingReceiptItem = new ReceivingReceiptItem(params)

        assert receivingReceiptItem.save() != null

        // test invalid parameters in update
        params.id = receivingReceiptItem.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/receivingReceiptItem/edit"
        assert model.receivingReceiptItemInstance != null

        receivingReceiptItem.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/receivingReceiptItem/show/$receivingReceiptItem.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        receivingReceiptItem.clearErrors()

        populateValidParams(params)
        params.id = receivingReceiptItem.id
        params.version = -1
        controller.update()

        assert view == "/receivingReceiptItem/edit"
        assert model.receivingReceiptItemInstance != null
        assert model.receivingReceiptItemInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/receivingReceiptItem/list'

        response.reset()

        populateValidParams(params)
        def receivingReceiptItem = new ReceivingReceiptItem(params)

        assert receivingReceiptItem.save() != null
        assert ReceivingReceiptItem.count() == 1

        params.id = receivingReceiptItem.id

        controller.delete()

        assert ReceivingReceiptItem.count() == 0
        assert ReceivingReceiptItem.get(receivingReceiptItem.id) == null
        assert response.redirectedUrl == '/receivingReceiptItem/list'
    }
}
