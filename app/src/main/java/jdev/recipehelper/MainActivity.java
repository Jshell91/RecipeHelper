package jdev.recipehelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    // Creamos el adaptador que usaremos.
    private RecyclerView.Adapter adapter;

    // Dataset de ejemplo para probar la funcionalidad.
    private String[] dataset = {"Magdalena", "Bizcocho", "Galletas" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creamos el recyclerView
        RecyclerView recyclerview;
        recyclerview = (RecyclerView) findViewById(R.id.recycler_view);

        // Creamos el layoutmanager y lo asignamos al recyclerview
        RecyclerView.LayoutManager manager;
        manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);

        // Creamos el adapter con los datos a usar y los añadimos al recyclerview
        adapter = new RecipeAdapter(dataset);
        recyclerview.setAdapter(adapter);
    }
}
