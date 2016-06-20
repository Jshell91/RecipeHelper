package jdev.recipehelper;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jshell on 19/06/2016.
 */

public class RecipeJson {

    public static JsonObject readJson(Context c) throws IOException {
        FileReader freader = new FileReader(c.getFilesDir().getPath() + "/recipeList.json");
        JsonReader jreader = new JsonReader(freader);
        jreader.setLenient(true);
        JsonParser parser = new JsonParser();
        JsonElement jelement = parser.parse(jreader);
        freader.close();

        return jelement.getAsJsonObject();
    }

    public static Recipe toRecipe(JsonObject jobject){

        return new Recipe(jobject.get("name").getAsString(),
                jobject.get("metric").getAsString(),
                jobject.get("quantity").getAsFloat(),
                jobject.get("price").getAsFloat(),
                jobject.get("cost").getAsFloat(),
                jobject.get("time").getAsString(),
                IngredientJson.toArrayList(jobject.getAsJsonArray("ingredientList")));
    }

    public static ArrayList<Recipe> toArrayList(JsonArray jarray){
        ArrayList<Recipe> ingredientList = new ArrayList<>();
        for (JsonElement jelement : jarray) {
            Recipe e = toRecipe(jelement.getAsJsonObject());
            ingredientList.add(e);
        }
        return ingredientList;
    }

    public static JsonObject toJson(Recipe e){
        JsonObject recipe = new JsonObject();
        recipe.addProperty("name", e.getName());
        recipe.addProperty("cost", e.getCost());
        recipe.addProperty("quantity", e.getQuantity());
        recipe.addProperty("metric", e.getMetric());
        recipe.addProperty("time", e.timetoString(e.getTime()));
        recipe.addProperty("price", e.getPrice());
        recipe.add("ingredientList", IngredientJson.toJsonArray(e.getIngredientList()));
        return recipe;
    }

    public static JsonArray toJsonArray(ArrayList<Recipe> recipeList){
        JsonArray jarray = new JsonArray();
        for (Recipe recipe : recipeList) {
            jarray.add(toJson(recipe));
        }
        return jarray;
    }

    public static void createJsonFile(File file) throws IOException {
        file.createNewFile();
        FileWriter fwriter = new FileWriter(file,false);
        fwriter.write(createJsonObject().toString());
        fwriter.close();
    }
    public static void writeJson(ArrayList<Recipe> rlist, Context c){
        try {
            File file = new File(c.getFilesDir().getPath() + "/recipeList.json");
            JsonObject jobject = RecipeJson.createJsonObject();
            jobject.getAsJsonArray("recipeList").addAll(RecipeJson.toJsonArray(rlist));

            FileWriter fwriter = new FileWriter(file, false);
            fwriter.write(jobject.toString());
            fwriter.close();
            Log.d("TAG", "saveIngredient: " + "hecho");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JsonObject createJsonObject(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("recipeList", new JsonArray());
        return jsonObject;
    }
}
