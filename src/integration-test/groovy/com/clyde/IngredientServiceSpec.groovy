package com.clyde

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class IngredientServiceSpec extends Specification {

    IngredientService ingredientService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Ingredient(...).save(flush: true, failOnError: true)
        //new Ingredient(...).save(flush: true, failOnError: true)
        //Ingredient ingredient = new Ingredient(...).save(flush: true, failOnError: true)
        //new Ingredient(...).save(flush: true, failOnError: true)
        //new Ingredient(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //ingredient.id
    }

    void "test get"() {
        setupData()

        expect:
        ingredientService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Ingredient> ingredientList = ingredientService.list(max: 2, offset: 2)

        then:
        ingredientList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        ingredientService.count() == 5
    }

    void "test delete"() {
        Long ingredientId = setupData()

        expect:
        ingredientService.count() == 5

        when:
        ingredientService.delete(ingredientId)
        sessionFactory.currentSession.flush()

        then:
        ingredientService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Ingredient ingredient = new Ingredient()
        ingredientService.save(ingredient)

        then:
        ingredient.id != null
    }
}
