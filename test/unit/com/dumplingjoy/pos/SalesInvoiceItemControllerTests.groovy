package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(SalesInvoiceItemController)
@Mock(SalesInvoiceItem)
class SalesInvoiceItemControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/salesInvoiceItem/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.salesInvoiceItemInstanceList.size() == 0
        assert model.salesInvoiceItemInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.salesInvoiceItemInstance != null
    }

    void testSave() {
        controller.save()

        assert model.salesInvoiceItemInstance != null
        assert view == '/salesInvoiceItem/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/salesInvoiceItem/show/1'
        assert controller.flash.message != null
        assert SalesInvoiceItem.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/salesInvoiceItem/list'


        populateValidParams(params)
        def salesInvoiceItem = new SalesInvoiceItem(params)

        assert salesInvoiceItem.save() != null

        params.id = salesInvoiceItem.id

        def model = controller.show()

        assert model.salesInvoiceItemInstance == salesInvoiceItem
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/salesInvoiceItem/list'


        populateValidParams(params)
        def salesInvoiceItem = new SalesInvoiceItem(params)

        assert salesInvoiceItem.save() != null

        params.id = salesInvoiceItem.id

        def model = controller.edit()

        assert model.salesInvoiceItemInstance == salesInvoiceItem
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/salesInvoiceItem/list'

        response.reset()


        populateValidParams(params)
        def salesInvoiceItem = new SalesInvoiceItem(params)

        assert salesInvoiceItem.save() != null

        // test invalid parameters in update
        params.id = salesInvoiceItem.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/salesInvoiceItem/edit"
        assert model.salesInvoiceItemInstance != null

        salesInvoiceItem.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/salesInvoiceItem/show/$salesInvoiceItem.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        salesInvoiceItem.clearErrors()

        populateValidParams(params)
        params.id = salesInvoiceItem.id
        params.version = -1
        controller.update()

        assert view == "/salesInvoiceItem/edit"
        assert model.salesInvoiceItemInstance != null
        assert model.salesInvoiceItemInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/salesInvoiceItem/list'

        response.reset()

        populateValidParams(params)
        def salesInvoiceItem = new SalesInvoiceItem(params)

        assert salesInvoiceItem.save() != null
        assert SalesInvoiceItem.count() == 1

        params.id = salesInvoiceItem.id

        controller.delete()

        assert SalesInvoiceItem.count() == 0
        assert SalesInvoiceItem.get(salesInvoiceItem.id) == null
        assert response.redirectedUrl == '/salesInvoiceItem/list'
    }
}
