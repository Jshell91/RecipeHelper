package jdev.recipehelper;

import android.media.Image;

import java.util.ArrayList;

/**
 * Created by dracc on 19/04/2016.
 */
public class Recipe {
    public String name;
    public Image photo;
    public String metric;
    public float quantity;
    public float cost;
    public float time;
    public ArrayList<Ingredient> ingredientList;
}
