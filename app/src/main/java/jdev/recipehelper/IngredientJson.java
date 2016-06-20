package jdev.recipehelper;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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

        return new Ingredient(jobject.get("name").getAsString(),
                jobject.get("type").getAsString(),
                jobject.get("state").getAsString(),
                jobject.get("metric").getAsString(),
                jobject.get("lot").getAsString(),
                jobject.get("quantity").getAsFloat(),
                jobject.get("cost").getAsDouble(),
                jobject.get("date").getAsString());
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
        ingredient.addProperty("date", e.getExpiration());
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

    public static void createJsonFile(File file) throws IOException {
        file.createNewFile();
        FileWriter fwriter = new FileWriter(file,false);
        fwriter.write(createJsonObject().toString());
        fwriter.close();
    }
    public static void writeJson(ArrayList<Ingredient> ilist, Context c){
        try {
            File file = new File(c.getFilesDir().getPath() + "/ingredientList.json");
            JsonObject jobject = IngredientJson.createJsonObject();
            jobject.getAsJsonArray("ingredientList").addAll(IngredientJson.toJsonArray(ilist));

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
        jsonObject.add("ingredientList", new JsonArray());
        return jsonObject;
    }
}
