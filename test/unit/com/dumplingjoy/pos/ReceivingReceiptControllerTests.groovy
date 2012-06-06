package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(ReceivingReceiptController)
@Mock(ReceivingReceipt)
class ReceivingReceiptControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/receivingReceipt/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.receivingReceiptInstanceList.size() == 0
        assert model.receivingReceiptInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.receivingReceiptInstance != null
    }

    void testSave() {
        controller.save()

        assert model.receivingReceiptInstance != null
        assert view == '/receivingReceipt/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/receivingReceipt/show/1'
        assert controller.flash.message != null
        assert ReceivingReceipt.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/receivingReceipt/list'


        populateValidParams(params)
        def receivingReceipt = new ReceivingReceipt(params)

        assert receivingReceipt.save() != null

        params.id = receivingReceipt.id

        def model = controller.show()

        assert model.receivingReceiptInstance == receivingReceipt
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/receivingReceipt/list'


        populateValidParams(params)
        def receivingReceipt = new ReceivingReceipt(params)

        assert receivingReceipt.save() != null

        params.id = receivingReceipt.id

        def model = controller.edit()

        assert model.receivingReceiptInstance == receivingReceipt
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/receivingReceipt/list'

        response.reset()


        populateValidParams(params)
        def receivingReceipt = new ReceivingReceipt(params)

        assert receivingReceipt.save() != null

        // test invalid parameters in update
        params.id = receivingReceipt.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/receivingReceipt/edit"
        assert model.receivingReceiptInstance != null

        receivingReceipt.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/receivingReceipt/show/$receivingReceipt.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        receivingReceipt.clearErrors()

        populateValidParams(params)
        params.id = receivingReceipt.id
        params.version = -1
        controller.update()

        assert view == "/receivingReceipt/edit"
        assert model.receivingReceiptInstance != null
        assert model.receivingReceiptInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/receivingReceipt/list'

        response.reset()

        populateValidParams(params)
        def receivingReceipt = new ReceivingReceipt(params)

        assert receivingReceipt.save() != null
        assert ReceivingReceipt.count() == 1

        params.id = receivingReceipt.id

        controller.delete()

        assert ReceivingReceipt.count() == 0
        assert ReceivingReceipt.get(receivingReceipt.id) == null
        assert response.redirectedUrl == '/receivingReceipt/list'
    }
}
