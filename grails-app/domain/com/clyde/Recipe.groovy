package com.clyde

class Recipe {

	String name
	String description
	String instructions
	double time
	static hasMany = [ingredients: Ingredient]
	static hasOne = [mealType: MealType]

    static constraints = {
		name size: 2..100, blank: false, unique:true
		description size: 5..300, blank: false
		instructions blank: false, maxSize: 1000
		time min: 0d
		hasMany nullable: false
    }
	
	// return the recipe name instead of the class name.
	String toString() {
		name
	}
}
