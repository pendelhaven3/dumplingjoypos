package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(AdjustmentOutController)
@Mock(AdjustmentOut)
class AdjustmentOutControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/adjustmentOut/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.adjustmentOutInstanceList.size() == 0
        assert model.adjustmentOutInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.adjustmentOutInstance != null
    }

    void testSave() {
        controller.save()

        assert model.adjustmentOutInstance != null
        assert view == '/adjustmentOut/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/adjustmentOut/show/1'
        assert controller.flash.message != null
        assert AdjustmentOut.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentOut/list'


        populateValidParams(params)
        def adjustmentOut = new AdjustmentOut(params)

        assert adjustmentOut.save() != null

        params.id = adjustmentOut.id

        def model = controller.show()

        assert model.adjustmentOutInstance == adjustmentOut
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentOut/list'


        populateValidParams(params)
        def adjustmentOut = new AdjustmentOut(params)

        assert adjustmentOut.save() != null

        params.id = adjustmentOut.id

        def model = controller.edit()

        assert model.adjustmentOutInstance == adjustmentOut
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentOut/list'

        response.reset()


        populateValidParams(params)
        def adjustmentOut = new AdjustmentOut(params)

        assert adjustmentOut.save() != null

        // test invalid parameters in update
        params.id = adjustmentOut.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/adjustmentOut/edit"
        assert model.adjustmentOutInstance != null

        adjustmentOut.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/adjustmentOut/show/$adjustmentOut.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        adjustmentOut.clearErrors()

        populateValidParams(params)
        params.id = adjustmentOut.id
        params.version = -1
        controller.update()

        assert view == "/adjustmentOut/edit"
        assert model.adjustmentOutInstance != null
        assert model.adjustmentOutInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentOut/list'

        response.reset()

        populateValidParams(params)
        def adjustmentOut = new AdjustmentOut(params)

        assert adjustmentOut.save() != null
        assert AdjustmentOut.count() == 1

        params.id = adjustmentOut.id

        controller.delete()

        assert AdjustmentOut.count() == 0
        assert AdjustmentOut.get(adjustmentOut.id) == null
        assert response.redirectedUrl == '/adjustmentOut/list'
    }
}
