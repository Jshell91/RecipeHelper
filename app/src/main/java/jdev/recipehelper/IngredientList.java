package jdev.recipehelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

        JsonObject jsonObject = null;

        File file = new File(this.getFilesDir().getPath() + "/ingredientList.json");

        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileReader freader = new FileReader(this.getFilesDir().getPath() + "/ingredientList.json");
            JsonParser parser = new JsonParser();
            JsonElement jelement = parser.parse(freader);
            jsonObject = jelement.getAsJsonObject();
            Log.d("TAG", "onCreate: " + jsonObject.toString());

            Ingredient ing = new Ingredient(jsonObject.get("name").getAsString(), jsonObject.get("quantity").getAsFloat(), jsonObject.get("cost").getAsDouble());
            Ingredient ing2 = new Ingredient("chafa", (float) 0.5, 0.25);
            ingredientlist.add(ing);
            ingredientlist.add(ing2);
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
