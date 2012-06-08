package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(ProductUnitCostController)
@Mock(ProductUnitCost)
class ProductUnitCostControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/productUnitCost/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.productUnitCostInstanceList.size() == 0
        assert model.productUnitCostInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.productUnitCostInstance != null
    }

    void testSave() {
        controller.save()

        assert model.productUnitCostInstance != null
        assert view == '/productUnitCost/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/productUnitCost/show/1'
        assert controller.flash.message != null
        assert ProductUnitCost.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/productUnitCost/list'


        populateValidParams(params)
        def productUnitCost = new ProductUnitCost(params)

        assert productUnitCost.save() != null

        params.id = productUnitCost.id

        def model = controller.show()

        assert model.productUnitCostInstance == productUnitCost
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/productUnitCost/list'


        populateValidParams(params)
        def productUnitCost = new ProductUnitCost(params)

        assert productUnitCost.save() != null

        params.id = productUnitCost.id

        def model = controller.edit()

        assert model.productUnitCostInstance == productUnitCost
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/productUnitCost/list'

        response.reset()


        populateValidParams(params)
        def productUnitCost = new ProductUnitCost(params)

        assert productUnitCost.save() != null

        // test invalid parameters in update
        params.id = productUnitCost.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/productUnitCost/edit"
        assert model.productUnitCostInstance != null

        productUnitCost.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/productUnitCost/show/$productUnitCost.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        productUnitCost.clearErrors()

        populateValidParams(params)
        params.id = productUnitCost.id
        params.version = -1
        controller.update()

        assert view == "/productUnitCost/edit"
        assert model.productUnitCostInstance != null
        assert model.productUnitCostInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/productUnitCost/list'

        response.reset()

        populateValidParams(params)
        def productUnitCost = new ProductUnitCost(params)

        assert productUnitCost.save() != null
        assert ProductUnitCost.count() == 1

        params.id = productUnitCost.id

        controller.delete()

        assert ProductUnitCost.count() == 0
        assert ProductUnitCost.get(productUnitCost.id) == null
        assert response.redirectedUrl == '/productUnitCost/list'
    }
}
