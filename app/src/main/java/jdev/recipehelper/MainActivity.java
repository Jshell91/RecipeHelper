package jdev.recipehelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    // Creamos el adaptador que usaremos.
    private RecyclerView.Adapter adapter;

    // Dataset de ejemplo para probar la funcionalidad.
    private ArrayList<Recipe> dataset = new ArrayList<Recipe>();
    Recipe rec1 = new Recipe("Magdalena");
    Recipe rec2 = new Recipe("Bizcocho");
    Recipe rec3 = new Recipe("Galleta");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataset.add(rec1);
        dataset.add(rec2);
        dataset.add(rec3);

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
        this.startActivity(intent);
    }

}

