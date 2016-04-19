package jdev.recipehelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private String[] dataset = {"Magdalena", "Bizcocho", "Galletas" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) findViewById(R.id.recycler_view);

        manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);

        adapter = new MyAdapter(dataset);
        recyclerview.setAdapter(adapter);
    }
}
