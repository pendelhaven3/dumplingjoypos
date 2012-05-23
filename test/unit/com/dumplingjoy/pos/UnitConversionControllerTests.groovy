package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(UnitConversionController)
@Mock(UnitConversion)
class UnitConversionControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/unitConversion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.unitConversionInstanceList.size() == 0
        assert model.unitConversionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.unitConversionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.unitConversionInstance != null
        assert view == '/unitConversion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/unitConversion/show/1'
        assert controller.flash.message != null
        assert UnitConversion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/unitConversion/list'


        populateValidParams(params)
        def unitConversion = new UnitConversion(params)

        assert unitConversion.save() != null

        params.id = unitConversion.id

        def model = controller.show()

        assert model.unitConversionInstance == unitConversion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/unitConversion/list'


        populateValidParams(params)
        def unitConversion = new UnitConversion(params)

        assert unitConversion.save() != null

        params.id = unitConversion.id

        def model = controller.edit()

        assert model.unitConversionInstance == unitConversion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/unitConversion/list'

        response.reset()


        populateValidParams(params)
        def unitConversion = new UnitConversion(params)

        assert unitConversion.save() != null

        // test invalid parameters in update
        params.id = unitConversion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/unitConversion/edit"
        assert model.unitConversionInstance != null

        unitConversion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/unitConversion/show/$unitConversion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        unitConversion.clearErrors()

        populateValidParams(params)
        params.id = unitConversion.id
        params.version = -1
        controller.update()

        assert view == "/unitConversion/edit"
        assert model.unitConversionInstance != null
        assert model.unitConversionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/unitConversion/list'

        response.reset()

        populateValidParams(params)
        def unitConversion = new UnitConversion(params)

        assert unitConversion.save() != null
        assert UnitConversion.count() == 1

        params.id = unitConversion.id

        controller.delete()

        assert UnitConversion.count() == 0
        assert UnitConversion.get(unitConversion.id) == null
        assert response.redirectedUrl == '/unitConversion/list'
    }
}
