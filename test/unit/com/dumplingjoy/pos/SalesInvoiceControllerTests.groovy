package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(SalesInvoiceController)
@Mock(SalesInvoice)
class SalesInvoiceControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/salesInvoice/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.salesInvoiceInstanceList.size() == 0
        assert model.salesInvoiceInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.salesInvoiceInstance != null
    }

    void testSave() {
        controller.save()

        assert model.salesInvoiceInstance != null
        assert view == '/salesInvoice/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/salesInvoice/show/1'
        assert controller.flash.message != null
        assert SalesInvoice.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/salesInvoice/list'


        populateValidParams(params)
        def salesInvoice = new SalesInvoice(params)

        assert salesInvoice.save() != null

        params.id = salesInvoice.id

        def model = controller.show()

        assert model.salesInvoiceInstance == salesInvoice
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/salesInvoice/list'


        populateValidParams(params)
        def salesInvoice = new SalesInvoice(params)

        assert salesInvoice.save() != null

        params.id = salesInvoice.id

        def model = controller.edit()

        assert model.salesInvoiceInstance == salesInvoice
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/salesInvoice/list'

        response.reset()


        populateValidParams(params)
        def salesInvoice = new SalesInvoice(params)

        assert salesInvoice.save() != null

        // test invalid parameters in update
        params.id = salesInvoice.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/salesInvoice/edit"
        assert model.salesInvoiceInstance != null

        salesInvoice.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/salesInvoice/show/$salesInvoice.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        salesInvoice.clearErrors()

        populateValidParams(params)
        params.id = salesInvoice.id
        params.version = -1
        controller.update()

        assert view == "/salesInvoice/edit"
        assert model.salesInvoiceInstance != null
        assert model.salesInvoiceInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/salesInvoice/list'

        response.reset()

        populateValidParams(params)
        def salesInvoice = new SalesInvoice(params)

        assert salesInvoice.save() != null
        assert SalesInvoice.count() == 1

        params.id = salesInvoice.id

        controller.delete()

        assert SalesInvoice.count() == 0
        assert SalesInvoice.get(salesInvoice.id) == null
        assert response.redirectedUrl == '/salesInvoice/list'
    }
}
