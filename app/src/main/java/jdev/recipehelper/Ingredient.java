package jdev.recipehelper;

import java.util.Date;

/**
 * Created by dracc on 19/04/2016.
 */
public class Ingredient {

    // Creamos las variables que usara.
    private String name;
    private String family;
    private String state;
    private String metric;
    private String lot;
    private float quantity;
    private float cost;
    private Date expiration;

    // Constructor basico con nombre y cantidad, solo de prueba el momento.
    public Ingredient(String name, float quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // Get necesarios para acceder a las variables.
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



}
