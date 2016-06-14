package jdev.recipehelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class IngredientList extends BaseActivity {
    ArrayList<Ingredient> ingredientlist = new ArrayList<Ingredient>();
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);

        recycler = (RecyclerView) findViewById(R.id.recycleringredient);

        File file = new File(this.getFilesDir().getPath() + "/ingredientList.json");

        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileReader freader = new FileReader(this.getFilesDir().getPath() + "/ingredientList.json");
            JsonReader jreader = new JsonReader(freader);
            jreader.setLenient(true);
            JsonParser parser = new JsonParser();

            JsonArray jarray = parser.parse(jreader).getAsJsonObject().getAsJsonArray("ingredientListajo");
            freader.close();
            Log.d("TAG", "onCreate: " + jarray.toString());
            for (JsonElement e : jarray) {
                JsonObject jobject = e.getAsJsonObject();
                Ingredient ing = new Ingredient(jobject.get("name").getAsString(), jobject.get("quantity").getAsFloat(), jobject.get("cost").getAsDouble());
                ingredientlist.add(ing);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "onCreate: " + e );
        }


        // Creamos el layoutmanager y lo asignamos al recyclerview
        RecyclerView.LayoutManager manager;
        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);


        RecyclerView.Adapter adapter = new IngredientAdapter(ingredientlist);
        recycler.setAdapter(adapter);
    }

    public void newIngredient(View v){
        Intent intent = new Intent(this, NewIngredient.class);

        this.startActivity(intent);
    }
}