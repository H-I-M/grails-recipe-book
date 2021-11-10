package com.clyde

import grails.gorm.services.Service

@Service(MealType)
interface MealTypeService {

    MealType get(Serializable id)

    List<MealType> list(Map args)

    Long count()

    void delete(Serializable id)

    MealType save(MealType mealType)

}