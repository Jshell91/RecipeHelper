package jdev.recipehelper;

import java.util.Date;

/**
 * Created by dracc on 19/04/2016.
 */
public class Ingredient {
    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getState() {
        return state;
    }

    public String getMetric() {
        return metric;
    }

    public String getLot() {
        return lot;
    }

    public float getQuantity() {
        return quantity;
    }

    public float getCost() {
        return cost;
    }

    public Date getExpiration() {
        return expiration;
    }

    public String name;
    public String family;
    public String state;
    public String metric;
    public String lot;
    public float quantity;
    public float cost;
    public Date expiration;

    public Ingredient(String name, float quantity) {
        this.name = name;
        this.quantity = quantity;
    }


}
