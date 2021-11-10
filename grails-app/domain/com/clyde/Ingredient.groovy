package com.clyde

class Ingredient {

	String name
	double amountInGrams
	static hasMany = [recipes: Recipe]
	static belongsTo = [recipes: Recipe]

    static constraints = {
		name size: 2..50, blank: false
		amountInGrams min: 0d
    }
	
	// return ingredient name instead of class name
	String toString() {
		name + " = " + amountInGrams + "g"
	}          
}
