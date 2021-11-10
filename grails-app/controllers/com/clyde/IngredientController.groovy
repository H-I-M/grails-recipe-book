package com.clyde

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class IngredientController {

    IngredientService ingredientService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ingredientService.list(params), model:[ingredientCount: ingredientService.count()]
    }

    def show(Long id) {
        respond ingredientService.get(id)
    }

    def create() {
        respond new Ingredient(params)
    }

    def save(Ingredient ingredient) {
        if (ingredient == null) {
            notFound()
            return
        }

        try {
            ingredientService.save(ingredient)
        } catch (ValidationException e) {
            respond ingredient.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ingredient.label', default: 'Ingredient'), ingredient.id])
                redirect action:"index", method:"GET"
            }
            '*' { respond ingredient, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond ingredientService.get(id)
    }

    def update(Ingredient ingredient) {
        if (ingredient == null) {
            notFound()
            return
        }

        try {
            ingredientService.save(ingredient)
        } catch (ValidationException e) {
            respond ingredient.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ingredient.label', default: 'Ingredient'), ingredient.id])
                redirect ingredient
            }
            '*'{ respond ingredient, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        ingredientService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ingredient.label', default: 'Ingredient'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ingredient.label', default: 'Ingredient'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
