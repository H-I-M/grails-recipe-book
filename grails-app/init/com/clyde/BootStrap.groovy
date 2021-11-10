package com.clyde

import com.clyde.MealType
import com.clyde.Recipe
import com.clyde.Ingredient

class BootStrap {

    def init = { servletContext ->
		def breakFast = new MealType(name: "Breakfast").save(failOnError: true)
		def lunch = new MealType(name: "Lunch").save(failOnError: true)
		def dinner = new MealType(name: "Dinner").save(failOnError: true)
		
		def ingr1 = new Ingredient(name: "Tomato", amountInGrams: 3).save(failOnError: true)
		def ingr2 = new Ingredient(name: "Pepper", amountInGrams: 0.2).save(failOnError: true)
		def ingr3 = new Ingredient(name: "BBQ Spice", amountInGrams: 2).save(failOnError: true)
		def ingr4 = new Ingredient(name: "Eggs", amountInGrams: 12.5).save(failOnError: true)
		def ingr5 = new Ingredient(name: "Rice", amountInGrams: 30).save(failOnError: true)
		
		def food1 = new Recipe(name: "Fried rice", description: "Asian fried rice", instructions: "Whatever Youtube says.", time: 35.5)
		food1.setMealType(lunch)
		food1.addToIngredients(ingr3)
		food1.addToIngredients(ingr4)
		food1.addToIngredients(ingr5)
		food1.save()
		
    }
    def destroy = {
    }
}
