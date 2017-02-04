package jdev.recipehelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;

public class IngredientList extends BaseActivity {
    ArrayList<Ingredient> ingredientlist = new ArrayList<Ingredient>();
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);

        recycler = (RecyclerView) findViewById(R.id.recyclerrecipe);

        File file = new File(this.getFilesDir().getPath() + "/ingredientList.json");

        try {
            if(!file.exists()){
                IngredientJson.createJsonFile(file);
            }
            JsonObject jobj = IngredientJson.readJson(this);
            ingredientlist = IngredientJson.toArrayList(jobj.getAsJsonArray("ingredientList"));

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "onCreate: " + e );
        }


        // Creamos el layoutmanager y lo asignamos al recyclerview
        RecyclerView.LayoutManager manager;
        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);


        RecyclerView.Adapter adapter = new IngredientListAdapter(ingredientlist, this);
        recycler.setAdapter(adapter);
    }

    public void newIngredient(View v){
        Intent intent = new Intent(this, NewIngredient.class);
        intent.putParcelableArrayListExtra("IngredientList", ingredientlist);
        this.startActivity(intent);
    }
}