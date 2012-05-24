package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(StockQuantityConversionController)
@Mock(StockQuantityConversion)
class StockQuantityConversionControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/stockQuantityConversion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.stockQuantityConversionInstanceList.size() == 0
        assert model.stockQuantityConversionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.stockQuantityConversionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.stockQuantityConversionInstance != null
        assert view == '/stockQuantityConversion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/stockQuantityConversion/show/1'
        assert controller.flash.message != null
        assert StockQuantityConversion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/stockQuantityConversion/list'


        populateValidParams(params)
        def stockQuantityConversion = new StockQuantityConversion(params)

        assert stockQuantityConversion.save() != null

        params.id = stockQuantityConversion.id

        def model = controller.show()

        assert model.stockQuantityConversionInstance == stockQuantityConversion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/stockQuantityConversion/list'


        populateValidParams(params)
        def stockQuantityConversion = new StockQuantityConversion(params)

        assert stockQuantityConversion.save() != null

        params.id = stockQuantityConversion.id

        def model = controller.edit()

        assert model.stockQuantityConversionInstance == stockQuantityConversion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/stockQuantityConversion/list'

        response.reset()


        populateValidParams(params)
        def stockQuantityConversion = new StockQuantityConversion(params)

        assert stockQuantityConversion.save() != null

        // test invalid parameters in update
        params.id = stockQuantityConversion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/stockQuantityConversion/edit"
        assert model.stockQuantityConversionInstance != null

        stockQuantityConversion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/stockQuantityConversion/show/$stockQuantityConversion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        stockQuantityConversion.clearErrors()

        populateValidParams(params)
        params.id = stockQuantityConversion.id
        params.version = -1
        controller.update()

        assert view == "/stockQuantityConversion/edit"
        assert model.stockQuantityConversionInstance != null
        assert model.stockQuantityConversionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/stockQuantityConversion/list'

        response.reset()

        populateValidParams(params)
        def stockQuantityConversion = new StockQuantityConversion(params)

        assert stockQuantityConversion.save() != null
        assert StockQuantityConversion.count() == 1

        params.id = stockQuantityConversion.id

        controller.delete()

        assert StockQuantityConversion.count() == 0
        assert StockQuantityConversion.get(stockQuantityConversion.id) == null
        assert response.redirectedUrl == '/stockQuantityConversion/list'
    }
}
