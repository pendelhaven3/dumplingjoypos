package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(AdjustmentInItemController)
@Mock(AdjustmentInItem)
class AdjustmentInItemControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/adjustmentInItem/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.adjustmentInItemInstanceList.size() == 0
        assert model.adjustmentInItemInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.adjustmentInItemInstance != null
    }

    void testSave() {
        controller.save()

        assert model.adjustmentInItemInstance != null
        assert view == '/adjustmentInItem/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/adjustmentInItem/show/1'
        assert controller.flash.message != null
        assert AdjustmentInItem.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentInItem/list'


        populateValidParams(params)
        def adjustmentInItem = new AdjustmentInItem(params)

        assert adjustmentInItem.save() != null

        params.id = adjustmentInItem.id

        def model = controller.show()

        assert model.adjustmentInItemInstance == adjustmentInItem
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentInItem/list'


        populateValidParams(params)
        def adjustmentInItem = new AdjustmentInItem(params)

        assert adjustmentInItem.save() != null

        params.id = adjustmentInItem.id

        def model = controller.edit()

        assert model.adjustmentInItemInstance == adjustmentInItem
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentInItem/list'

        response.reset()


        populateValidParams(params)
        def adjustmentInItem = new AdjustmentInItem(params)

        assert adjustmentInItem.save() != null

        // test invalid parameters in update
        params.id = adjustmentInItem.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/adjustmentInItem/edit"
        assert model.adjustmentInItemInstance != null

        adjustmentInItem.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/adjustmentInItem/show/$adjustmentInItem.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        adjustmentInItem.clearErrors()

        populateValidParams(params)
        params.id = adjustmentInItem.id
        params.version = -1
        controller.update()

        assert view == "/adjustmentInItem/edit"
        assert model.adjustmentInItemInstance != null
        assert model.adjustmentInItemInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentInItem/list'

        response.reset()

        populateValidParams(params)
        def adjustmentInItem = new AdjustmentInItem(params)

        assert adjustmentInItem.save() != null
        assert AdjustmentInItem.count() == 1

        params.id = adjustmentInItem.id

        controller.delete()

        assert AdjustmentInItem.count() == 0
        assert AdjustmentInItem.get(adjustmentInItem.id) == null
        assert response.redirectedUrl == '/adjustmentInItem/list'
    }
}
