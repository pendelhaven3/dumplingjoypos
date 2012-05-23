package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(AdjustmentInController)
@Mock(AdjustmentIn)
class AdjustmentInControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/adjustmentIn/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.adjustmentInInstanceList.size() == 0
        assert model.adjustmentInInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.adjustmentInInstance != null
    }

    void testSave() {
        controller.save()

        assert model.adjustmentInInstance != null
        assert view == '/adjustmentIn/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/adjustmentIn/show/1'
        assert controller.flash.message != null
        assert AdjustmentIn.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentIn/list'


        populateValidParams(params)
        def adjustmentIn = new AdjustmentIn(params)

        assert adjustmentIn.save() != null

        params.id = adjustmentIn.id

        def model = controller.show()

        assert model.adjustmentInInstance == adjustmentIn
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentIn/list'


        populateValidParams(params)
        def adjustmentIn = new AdjustmentIn(params)

        assert adjustmentIn.save() != null

        params.id = adjustmentIn.id

        def model = controller.edit()

        assert model.adjustmentInInstance == adjustmentIn
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentIn/list'

        response.reset()


        populateValidParams(params)
        def adjustmentIn = new AdjustmentIn(params)

        assert adjustmentIn.save() != null

        // test invalid parameters in update
        params.id = adjustmentIn.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/adjustmentIn/edit"
        assert model.adjustmentInInstance != null

        adjustmentIn.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/adjustmentIn/show/$adjustmentIn.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        adjustmentIn.clearErrors()

        populateValidParams(params)
        params.id = adjustmentIn.id
        params.version = -1
        controller.update()

        assert view == "/adjustmentIn/edit"
        assert model.adjustmentInInstance != null
        assert model.adjustmentInInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/adjustmentIn/list'

        response.reset()

        populateValidParams(params)
        def adjustmentIn = new AdjustmentIn(params)

        assert adjustmentIn.save() != null
        assert AdjustmentIn.count() == 1

        params.id = adjustmentIn.id

        controller.delete()

        assert AdjustmentIn.count() == 0
        assert AdjustmentIn.get(adjustmentIn.id) == null
        assert response.redirectedUrl == '/adjustmentIn/list'
    }
}
