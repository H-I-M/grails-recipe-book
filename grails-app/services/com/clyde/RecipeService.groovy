package com.clyde

import grails.gorm.services.Service

@Service(Recipe)
interface RecipeService {

    Recipe get(Serializable id)

    List<Recipe> list(Map args)

    Long count()

    void delete(Serializable id)

    Recipe save(Recipe recipe)

    List<Recipe> findByNameLike(String name, Map args)

}
