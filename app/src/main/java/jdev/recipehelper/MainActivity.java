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

public class MainActivity extends BaseActivity {

    // Creamos el adaptador que usaremos.
    private RecyclerView.Adapter adapter;

    // Dataset de ejemplo para probar la funcionalidad.
    private ArrayList<Recipe> dataset = new ArrayList<Recipe>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = new File(this.getFilesDir().getPath() + "/recipeList.json");

        try {
            if(!file.exists()){
                RecipeJson.createJsonFile(file);
            }
            JsonObject jobj = RecipeJson.readJson(this);
            dataset = RecipeJson.toArrayList(jobj.getAsJsonArray("recipeList"));

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "onCreate: " + e );
        }

        // Creamos el recyclerView
        RecyclerView recyclerview;
        recyclerview = (RecyclerView) findViewById(R.id.recyclerrecipe);

        // Creamos el layoutmanager y lo asignamos al recyclerview
        RecyclerView.LayoutManager manager;
        manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);

        // Creamos el adapter con los datos a usar y los añadimos al recyclerview
        adapter = new RecipeAdapter(dataset);
        recyclerview.setAdapter(adapter);
    }

    public void newRecipe(View v){
        Intent intent = new Intent(this, NewRecipe.class);
        intent.putParcelableArrayListExtra("RecipeList", dataset);
        this.startActivity(intent);
    }

}

