package com.clyde

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MealTypeServiceSpec extends Specification {

    MealTypeService mealTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new MealType(...).save(flush: true, failOnError: true)
        //new MealType(...).save(flush: true, failOnError: true)
        //MealType mealType = new MealType(...).save(flush: true, failOnError: true)
        //new MealType(...).save(flush: true, failOnError: true)
        //new MealType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //mealType.id
    }

    void "test get"() {
        setupData()

        expect:
        mealTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<MealType> mealTypeList = mealTypeService.list(max: 2, offset: 2)

        then:
        mealTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        mealTypeService.count() == 5
    }

    void "test delete"() {
        Long mealTypeId = setupData()

        expect:
        mealTypeService.count() == 5

        when:
        mealTypeService.delete(mealTypeId)
        sessionFactory.currentSession.flush()

        then:
        mealTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        MealType mealType = new MealType()
        mealTypeService.save(mealType)

        then:
        mealType.id != null
    }
}
