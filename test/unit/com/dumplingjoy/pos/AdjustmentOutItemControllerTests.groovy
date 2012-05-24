package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(AdjustmentOutItemController)
@Mock(AdjustmentOutItem)
class AdjustmentOutItemControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/adjustmentOutItem/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.adjustmentOutItemInstanceList.size() == 0
        assert model.adjustmentOutItemInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.adjustmentOutItemInstance != null
    }

    void testSave() {
        controller.save()

        assert model.adjustmentOutItemInstance != null
        assert view == '/adjustmentOutItem/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/adjustmentOutItem/show/1'
        assert controller.flash.message != null
        assert AdjustmentOutItem.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentOutItem/list'


        populateValidParams(params)
        def adjustmentOutItem = new AdjustmentOutItem(params)

        assert adjustmentOutItem.save() != null

        params.id = adjustmentOutItem.id

        def model = controller.show()

        assert model.adjustmentOutItemInstance == adjustmentOutItem
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentOutItem/list'


        populateValidParams(params)
        def adjustmentOutItem = new AdjustmentOutItem(params)

        assert adjustmentOutItem.save() != null

        params.id = adjustmentOutItem.id

        def model = controller.edit()

        assert model.adjustmentOutItemInstance == adjustmentOutItem
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentOutItem/list'

        response.reset()


        populateValidParams(params)
        def adjustmentOutItem = new AdjustmentOutItem(params)

        assert adjustmentOutItem.save() != null

        // test invalid parameters in update
        params.id = adjustmentOutItem.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/adjustmentOutItem/edit"
        assert model.adjustmentOutItemInstance != null

        adjustmentOutItem.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/adjustmentOutItem/show/$adjustmentOutItem.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        adjustmentOutItem.clearErrors()

        populateValidParams(params)
        params.id = adjustmentOutItem.id
        params.version = -1
        controller.update()

        assert view == "/adjustmentOutItem/edit"
        assert model.adjustmentOutItemInstance != null
        assert model.adjustmentOutItemInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentOutItem/list'

        response.reset()

        populateValidParams(params)
        def adjustmentOutItem = new AdjustmentOutItem(params)

        assert adjustmentOutItem.save() != null
        assert AdjustmentOutItem.count() == 1

        params.id = adjustmentOutItem.id

        controller.delete()

        assert AdjustmentOutItem.count() == 0
        assert AdjustmentOutItem.get(adjustmentOutItem.id) == null
        assert response.redirectedUrl == '/adjustmentOutItem/list'
    }
}
