package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(ProductUnitPriceController)
@Mock(ProductUnitPrice)
class ProductUnitPriceControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/productUnitPrice/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.productUnitPriceInstanceList.size() == 0
        assert model.productUnitPriceInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.productUnitPriceInstance != null
    }

    void testSave() {
        controller.save()

        assert model.productUnitPriceInstance != null
        assert view == '/productUnitPrice/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/productUnitPrice/show/1'
        assert controller.flash.message != null
        assert ProductUnitPrice.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/productUnitPrice/list'


        populateValidParams(params)
        def productUnitPrice = new ProductUnitPrice(params)

        assert productUnitPrice.save() != null

        params.id = productUnitPrice.id

        def model = controller.show()

        assert model.productUnitPriceInstance == productUnitPrice
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/productUnitPrice/list'


        populateValidParams(params)
        def productUnitPrice = new ProductUnitPrice(params)

        assert productUnitPrice.save() != null

        params.id = productUnitPrice.id

        def model = controller.edit()

        assert model.productUnitPriceInstance == productUnitPrice
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/productUnitPrice/list'

        response.reset()


        populateValidParams(params)
        def productUnitPrice = new ProductUnitPrice(params)

        assert productUnitPrice.save() != null

        // test invalid parameters in update
        params.id = productUnitPrice.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/productUnitPrice/edit"
        assert model.productUnitPriceInstance != null

        productUnitPrice.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/productUnitPrice/show/$productUnitPrice.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        productUnitPrice.clearErrors()

        populateValidParams(params)
        params.id = productUnitPrice.id
        params.version = -1
        controller.update()

        assert view == "/productUnitPrice/edit"
        assert model.productUnitPriceInstance != null
        assert model.productUnitPriceInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/productUnitPrice/list'

        response.reset()

        populateValidParams(params)
        def productUnitPrice = new ProductUnitPrice(params)

        assert productUnitPrice.save() != null
        assert ProductUnitPrice.count() == 1

        params.id = productUnitPrice.id

        controller.delete()

        assert ProductUnitPrice.count() == 0
        assert ProductUnitPrice.get(productUnitPrice.id) == null
        assert response.redirectedUrl == '/productUnitPrice/list'
    }
}
