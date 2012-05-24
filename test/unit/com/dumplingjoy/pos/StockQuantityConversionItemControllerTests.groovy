package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(StockQuantityConversionItemController)
@Mock(StockQuantityConversionItem)
class StockQuantityConversionItemControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/stockQuantityConversionItem/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.stockQuantityConversionItemInstanceList.size() == 0
        assert model.stockQuantityConversionItemInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.stockQuantityConversionItemInstance != null
    }

    void testSave() {
        controller.save()

        assert model.stockQuantityConversionItemInstance != null
        assert view == '/stockQuantityConversionItem/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/stockQuantityConversionItem/show/1'
        assert controller.flash.message != null
        assert StockQuantityConversionItem.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/stockQuantityConversionItem/list'


        populateValidParams(params)
        def stockQuantityConversionItem = new StockQuantityConversionItem(params)

        assert stockQuantityConversionItem.save() != null

        params.id = stockQuantityConversionItem.id

        def model = controller.show()

        assert model.stockQuantityConversionItemInstance == stockQuantityConversionItem
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/stockQuantityConversionItem/list'


        populateValidParams(params)
        def stockQuantityConversionItem = new StockQuantityConversionItem(params)

        assert stockQuantityConversionItem.save() != null

        params.id = stockQuantityConversionItem.id

        def model = controller.edit()

        assert model.stockQuantityConversionItemInstance == stockQuantityConversionItem
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/stockQuantityConversionItem/list'

        response.reset()


        populateValidParams(params)
        def stockQuantityConversionItem = new StockQuantityConversionItem(params)

        assert stockQuantityConversionItem.save() != null

        // test invalid parameters in update
        params.id = stockQuantityConversionItem.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/stockQuantityConversionItem/edit"
        assert model.stockQuantityConversionItemInstance != null

        stockQuantityConversionItem.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/stockQuantityConversionItem/show/$stockQuantityConversionItem.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        stockQuantityConversionItem.clearErrors()

        populateValidParams(params)
        params.id = stockQuantityConversionItem.id
        params.version = -1
        controller.update()

        assert view == "/stockQuantityConversionItem/edit"
        assert model.stockQuantityConversionItemInstance != null
        assert model.stockQuantityConversionItemInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/stockQuantityConversionItem/list'

        response.reset()

        populateValidParams(params)
        def stockQuantityConversionItem = new StockQuantityConversionItem(params)

        assert stockQuantityConversionItem.save() != null
        assert StockQuantityConversionItem.count() == 1

        params.id = stockQuantityConversionItem.id

        controller.delete()

        assert StockQuantityConversionItem.count() == 0
        assert StockQuantityConversionItem.get(stockQuantityConversionItem.id) == null
        assert response.redirectedUrl == '/stockQuantityConversionItem/list'
    }
}
