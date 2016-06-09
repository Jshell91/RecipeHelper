package jdev.recipehelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;

public class Calculator extends BaseActivity {
    // Clase que nos calculara las porciones y el coste de nuestra receta.
    // La funcionalida aun no esta implementada, primero estoy implementando la interfaz.

    private TextView tvname;
    private TextView tvnumcost;
    private RecyclerView.Adapter adapter;
    private Recipe recipe;

    private double cost = 0;

    private double costtime = 6.0;
    private double repay = 1;

    // Dataset de ejemplo para probar la funcionalidad.
    public ArrayList<Ingredient> data = new ArrayList<Ingredient>();
    Ingredient ing1 = new Ingredient("harina", 500, 0.5);
    Ingredient ing2 = new Ingredient("azucar", 50, 0.1);
    Ingredient ing3 = new Ingredient("aceite", 5, 0.25);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvname = (TextView) findViewById(R.id.tvrecname);
        tvnumcost = (TextView) findViewById(R.id.tvnumcost);
        // Añadimos los objetos al ArrayList
        data.add(ing1);
        data.add(ing2);
        data.add(ing3);

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

        tvname.setText(getIntent().getExtras().get("Nombre").toString());

        setCostText(data);
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

    public double getCosttime() {
        return costtime;
    }

    public void setCosttime(double costtime) {
        this.costtime = costtime;
    }

    public double getRepay() {
        return repay;
    }

    public void setRepay(double repay) {
        this.repay = repay;
    }

    public void setCostText(ArrayList<Ingredient> data) {
        cost = 0;
        for (Ingredient e : data) {
            cost += (e.getCost() * e.getQuantity());
        }
        tvnumcost.setText(String.format("%.2f",cost));
    }
}
