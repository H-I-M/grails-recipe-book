package com.clyde

import grails.test.hibernate.HibernateSpec
import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import spock.lang.*

@SuppressWarnings('MethodName')
class RecipeControllerSpec extends HibernateSpec implements ControllerUnitTest<RecipeController>, DomainUnitTest<Recipe> {

    static doWithSpring = {
        jsonSmartViewResolver(JsonViewResolver)
    }

    def populateValidParams(params) {
        assert params != null
        params.name = 'Recipe ' + UUID.randomUUID().toString()
        params.description = 'Description'
        params.instructions = 'Instructions'
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.recipeService = Mock(RecipeService) {
            1 * list(_) >> []
            1 * count() >> 0
        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.recipeList
        model.recipeCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.recipe!= null
    }

    void "Test the save action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.save(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/recipe'
        flash.message != null
    }

    void "Test the save action correctly persists"() {
        given:
        controller.recipeService = Mock(RecipeService) {
            1 * save(_ as Recipe)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        def recipe = new Recipe(params)
        recipe.id = 1

        controller.save(recipe)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/recipe/show/1'
        controller.flash.message != null
    }

    void "Test the save action with an invalid instance"() {
        given:
        controller.recipeService = Mock(RecipeService) {
            1 * save(_ as Recipe) >> { Recipe recipe ->
                throw new ValidationException("Invalid instance", recipe.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def recipe = new Recipe()
        controller.save(recipe)

        then:"The create view is rendered again with the correct model"
        model.recipe != null
        view == 'create'
    }

    void "Test the show action with a null id"() {
        given:
        controller.recipeService = Mock(RecipeService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id"() {
        given:
        controller.recipeService = Mock(RecipeService) {
            1 * get(2) >> new Recipe()
        }

        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"A model is populated containing the domain instance"
        model.recipe instanceof Recipe
    }

    void "Test the edit action with a null id"() {
        given:
        controller.recipeService = Mock(RecipeService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the edit action with a valid id"() {
        given:
        controller.recipeService = Mock(RecipeService) {
            1 * get(2) >> new Recipe()
        }

        when:"A domain instance is passed to the show action"
        controller.edit(2)

        then:"A model is populated containing the domain instance"
        model.recipe instanceof Recipe
    }


    void "Test the update action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/recipe'
        flash.message != null
    }

    void "Test the update action correctly persists"() {
        given:
        controller.recipeService = Mock(RecipeService) {
            1 * save(_ as Recipe)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParams(params)
        def recipe = new Recipe(params)
        recipe.id = 1

        controller.update(recipe)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/recipe/show/1'
        controller.flash.message != null
    }

    void "Test the update action with an invalid instance"() {
        given:
        controller.recipeService = Mock(RecipeService) {
            1 * save(_ as Recipe) >> { Recipe recipe ->
                throw new ValidationException("Invalid instance", recipe.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(new Recipe())

        then:"The edit view is rendered again with the correct model"
        model.recipe != null
        view == 'edit'
    }

    void "Test the delete action with a null instance"() {
        when:"The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then:"A 404 is returned"
        response.redirectedUrl == '/recipe'
        flash.message != null
    }

    void "Test the delete action with an instance"() {
        given:
        controller.recipeService = Mock(RecipeService) {
            1 * delete(2)
        }

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(2)

        then:"The user is redirected to index"
        response.redirectedUrl == '/recipe'
        flash.message != null
    }

    void 'test the search action finds results'() {
        given:
        request.addHeader 'Accept', JSON_CONTENT_TYPE
        request.format = 'json'
        controller.recipeService = Stub(RecipeService) {
            findByNameLike(_, _) >> [new Recipe(name: "Apple pie", description: "Yummy pie", instructions: "Apples and pie", time: 60)]
        }
        when: 'A query is executed that finds results'
        controller.search('pp', 10)

        then: 'The response is correct'
        response.status == 200
        response.contentType.contains(JSON_CONTENT_TYPE)
        response.json.size() == 1
        response.json[0].name == 'Apple pie'
    }

}






