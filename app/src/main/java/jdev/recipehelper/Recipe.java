package jdev.recipehelper;

import android.media.Image;

import java.util.ArrayList;

/**
 * Created by dracc on 19/04/2016.
 */
public class Recipe {

    // Creamos las variables a usar
    private String name;
    private Image photo;
    private String metric;
    private float quantity;
    private float cost;
    private float time;
    private ArrayList<Ingredient> ingredientList;

    // Constructor con variables necesarias
    public Recipe(String name, float quantity, float cost, float time, ArrayList<Ingredient> ingredientList) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.time = time;
        this.ingredientList = ingredientList;
    }

    public Recipe(String name) {
        this.name = name;
    }

    // Get necesarios para acceder a las variables.
    public String getName() {
        return name;
    }

    public Image getPhoto() {
        return photo;
    }

    public String getMetric() {
        return metric;
    }

    public float getQuantity() {
        return quantity;
    }

    public float getCost() {
        return cost;
    }

    public float getTime() {
        return time;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }
}
