package com.dumplingjoy.pos



import org.junit.*
import grails.test.mixin.*

@TestFor(PricingSchemeController)
@Mock(PricingScheme)
class PricingSchemeControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pricingScheme/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pricingSchemeInstanceList.size() == 0
        assert model.pricingSchemeInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.pricingSchemeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pricingSchemeInstance != null
        assert view == '/pricingScheme/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pricingScheme/show/1'
        assert controller.flash.message != null
        assert PricingScheme.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pricingScheme/list'


        populateValidParams(params)
        def pricingScheme = new PricingScheme(params)

        assert pricingScheme.save() != null

        params.id = pricingScheme.id

        def model = controller.show()

        assert model.pricingSchemeInstance == pricingScheme
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pricingScheme/list'


        populateValidParams(params)
        def pricingScheme = new PricingScheme(params)

        assert pricingScheme.save() != null

        params.id = pricingScheme.id

        def model = controller.edit()

        assert model.pricingSchemeInstance == pricingScheme
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pricingScheme/list'

        response.reset()


        populateValidParams(params)
        def pricingScheme = new PricingScheme(params)

        assert pricingScheme.save() != null

        // test invalid parameters in update
        params.id = pricingScheme.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pricingScheme/edit"
        assert model.pricingSchemeInstance != null

        pricingScheme.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pricingScheme/show/$pricingScheme.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pricingScheme.clearErrors()

        populateValidParams(params)
        params.id = pricingScheme.id
        params.version = -1
        controller.update()

        assert view == "/pricingScheme/edit"
        assert model.pricingSchemeInstance != null
        assert model.pricingSchemeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pricingScheme/list'

        response.reset()

        populateValidParams(params)
        def pricingScheme = new PricingScheme(params)

        assert pricingScheme.save() != null
        assert PricingScheme.count() == 1

        params.id = pricingScheme.id

        controller.delete()

        assert PricingScheme.count() == 0
        assert PricingScheme.get(pricingScheme.id) == null
        assert response.redirectedUrl == '/pricingScheme/list'
    }
}
