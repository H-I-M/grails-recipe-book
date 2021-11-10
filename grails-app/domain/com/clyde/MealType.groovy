package com.clyde

import grails.rest.*

class MealType {

	String name
	static hasMany = [recipes: Recipe]
	static belongsTo = [recipes: Recipe]

    static constraints = {
		name size: 2..20, blank: false
    }
	
	// return meal type name instead of class name
	String toString() {
		name
	}
}
