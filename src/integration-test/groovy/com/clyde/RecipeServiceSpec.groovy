package com.clyde

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RecipeServiceSpec extends Specification {

    RecipeService recipeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Recipe(...).save(flush: true, failOnError: true)
        //new Recipe(...).save(flush: true, failOnError: true)
        //Recipe recipe = new Recipe(...).save(flush: true, failOnError: true)
        //new Recipe(...).save(flush: true, failOnError: true)
        //new Recipe(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //recipe.id
    }

    void "test get"() {
        setupData()

        expect:
        recipeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Recipe> recipeList = recipeService.list(max: 2, offset: 2)

        then:
        recipeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        recipeService.count() == 5
    }

    void "test delete"() {
        Long recipeId = setupData()

        expect:
        recipeService.count() == 5

        when:
        recipeService.delete(recipeId)
        sessionFactory.currentSession.flush()

        then:
        recipeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Recipe recipe = new Recipe()
        recipeService.save(recipe)

        then:
        recipe.id != null
    }
}
