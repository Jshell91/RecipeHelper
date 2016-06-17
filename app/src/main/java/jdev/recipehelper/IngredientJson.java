package jdev.recipehelper;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jshell on 16/06/2016.
 */

public class IngredientJson {

    public static JsonObject readJson(Context c) throws IOException {
        FileReader freader = new FileReader(c.getFilesDir().getPath() + "/ingredientList.json");
        JsonReader jreader = new JsonReader(freader);
        jreader.setLenient(true);
        JsonParser parser = new JsonParser();
        JsonElement jelement = parser.parse(jreader);
        freader.close();

        return jelement.getAsJsonObject();
    }

    public static Ingredient toIngredient(JsonObject jobject){

        return new Ingredient(jobject.get("name").getAsString(), jobject.get("type").getAsString(),
                jobject.get("state").getAsString(), jobject.get("metric").getAsString(),
                jobject.get("lot").getAsString(), jobject.get("quantity").getAsFloat(),
                jobject.get("cost").getAsDouble(), jobject.get("date").getAsString());
    }

    public static ArrayList<Ingredient> toArrayList(JsonArray jarray){
        ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
        for (JsonElement jelement : jarray) {
            Ingredient e = toIngredient(jelement.getAsJsonObject());
            ingredientList.add(e);
        }
        return ingredientList;
    }

    public static JsonObject toJson(Ingredient e){
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("name", e.getName());
        ingredient.addProperty("type", e.getType());
        ingredient.addProperty("cost", e.getCost());
        ingredient.addProperty("quantity", e.getQuantity());
        ingredient.addProperty("metric", e.getMetric());
        ingredient.addProperty("lot", e.getLot());
        ingredient.addProperty("expiration", e.getExpiration());
        ingredient.addProperty("state", e.getState());

        return ingredient;
    }

    public static JsonArray toJsonArray(ArrayList<Ingredient> ingredientlist){
        JsonArray jarray = new JsonArray();
        for (Ingredient ingredient : ingredientlist) {
             jarray.add(toJson(ingredient));
        }
        return jarray;
    }


}
