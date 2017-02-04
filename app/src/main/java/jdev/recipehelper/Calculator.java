package jdev.recipehelper;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

public class Calculator extends BaseActivity {
    // Clase que nos calculara las porciones y el coste de nuestra receta.
    // La funcionalida aun no esta implementada, primero estoy implementando la interfaz.

    private TextView tvname, tvnumcost, quantity, time;
    private RecyclerView.Adapter adapter;
    private Recipe recipe;

    private double cost = 0;

    // Dataset de ejemplo para probar la funcionalidad.
    public ArrayList<Ingredient> data = new ArrayList<Ingredient>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recipe = getIntent().getExtras().getParcelable("Recipe");

        tvname = (TextView) findViewById(R.id.tvrecname);
        tvnumcost = (TextView) findViewById(R.id.tvnumcost);
        quantity = (TextView) findViewById(R.id.tvnumquantity);
        time = (TextView) findViewById(R.id.tvnumtime);

        // Creamos el recyclerView
        RecyclerView recyclerview;
        recyclerview = (RecyclerView) findViewById(R.id.ing_recycler);

        // Creamos el layoutmanager y lo asignamos al recyclerview
        RecyclerView.LayoutManager manager;
        manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);

        // Creamos el adapter con los datos a usar y los añadimos al recyclerview
        adapter = new IngredientAdapter(this, data);
        recyclerview.setAdapter(adapter);

        tvname.setText(recipe.getName());
        recipe.setCost();
        setTextCost();
        quantity.setText("" + recipe.getQuantity() + " " + recipe.getMetric());
        time.setText(recipe.timetoString(recipe.getTime()));
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setQuantityText(ArrayList<Ingredient> data) {

    }

    public void setTimeText(ArrayList<Ingredient> data) {

    }

    public TextView getTvname() {
        return tvname;
    }

    public TextView getTvnumcost() {
        return tvnumcost;
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public ArrayList<Ingredient> getData() {
        return data;
    }

    public void setTextCost() {
        tvnumcost.setText("" + recipe.getCost());
    }

    public void setData(ArrayList<Ingredient> data) {
        this.data = data;
    }
}
