package jdev.recipehelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;

    private String[] dataset = {"Magdalena", "Bizcocho", "Galletas" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerview;
        recyclerview = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager manager;
        manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);

        adapter = new RecipeAdapter(dataset);
        recyclerview.setAdapter(adapter);
    }
}
