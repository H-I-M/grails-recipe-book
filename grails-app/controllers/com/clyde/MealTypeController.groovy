package com.clyde

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MealTypeController {

    MealTypeService mealTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond mealTypeService.list(params), model:[mealTypeCount: mealTypeService.count()]
    }

    def show(Long id) {
        respond mealTypeService.get(id)
    }

    def create() {
        respond new MealType(params)
    }

    def save(MealType mealType) {
        if (mealType == null) {
            notFound()
            return
        }

        try {
            mealTypeService.save(mealType)
        } catch (ValidationException e) {
            respond mealType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mealType.label', default: 'MealType'), mealType.id])
                redirect action:"index", method:"GET"
            }
            '*' { respond mealType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond mealTypeService.get(id)
    }

    def update(MealType mealType) {
        if (mealType == null) {
            notFound()
            return
        }

        try {
            mealTypeService.save(mealType)
        } catch (ValidationException e) {
            respond mealType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'mealType.label', default: 'MealType'), mealType.id])
                redirect mealType
            }
            '*'{ respond mealType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        mealTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'mealType.label', default: 'MealType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mealType.label', default: 'MealType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
