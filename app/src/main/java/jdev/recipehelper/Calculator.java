package jdev.recipehelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class Calculator extends AppCompatActivity {

    private RecyclerView.Adapter adapter;

    public ArrayList<Ingredient> data = new ArrayList<Ingredient>();
    Ingredient ing1 = new Ingredient("harina", 500);
    Ingredient ing2 = new Ingredient("azucar", 50);
    Ingredient ing3 = new Ingredient("aceite", 5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        data.add(ing1);
        data.add(ing2);
        data.add(ing3);

        RecyclerView recyclerview;
        recyclerview = (RecyclerView) findViewById(R.id.ing_recycler);

        RecyclerView.LayoutManager manager;
        manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);

        adapter = new IngredientAdapter(data);
        recyclerview.setAdapter(adapter);
    }
}
