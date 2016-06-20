package jdev.recipehelper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

public class NewRecipe extends BaseActivity {
    Recipe recipe;
    ArrayList<Ingredient> ingredientlist = new ArrayList<Ingredient>();
    ArrayList<Ingredient> baselist = new ArrayList<Ingredient>();
    ArrayList<Recipe> recipelist = new ArrayList<>();
    IngredientListDialog dfragment;
    RecyclerView recycler;
    TextView cost;
    EditText name, quantity, time;
    Spinner metric;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newrecipe);
        recycler = (RecyclerView) findViewById(R.id.recyclernewrecipe);
        cost = (TextView) findViewById(R.id.etnewreccost);
        name = (EditText) findViewById(R.id.etnewrecname);
        quantity = (EditText) findViewById(R.id.etnewrecquantity);
        time = (EditText) findViewById(R.id.etnewrectime);
        metric = (Spinner) findViewById(R.id.spinnerrecmetric);
        recipe = new Recipe();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            recipelist = bundle.getParcelableArrayList("RecipeList");
            Recipe e = bundle.getParcelable("Recipe");
            if(e != null){setInitialValues(e);}
        }

        // Creamos el layoutmanager y lo asignamos al recyclerview
        RecyclerView.LayoutManager manager;
        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);

        RecyclerView.Adapter adapter = new IngredientAdapter(ingredientlist);
        recycler.setAdapter(adapter);
        try {
            JsonObject jobj = IngredientJson.readJson(this);
            baselist = IngredientJson.toArrayList(jobj.getAsJsonArray("ingredientList"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear(View v){
        cost.setText("1.0");
        name.setText("Nombre");
        quantity.setText("Cantidad");
        time.setText("");
        metric.setSelection(0);
        recycler.setAdapter(new IngredientAdapter(new ArrayList<Ingredient>()));
    }

    public void saveRecipe(View v){
        String quan = quantity.getText().toString();
        if(quan.equals("Cantidad")){quan=""+0;}
        recipe = new Recipe(name.getText().toString(), metric.getSelectedItem().toString(),
                Float.parseFloat(quan), 0, 0, time.getText().toString(), ingredientlist);
        recipe.setCost();
        recipe.setPrice(recipe.getCost());
        if(!recipelist.contains(recipe)){
            recipelist.add(recipe);
            RecipeJson.writeJson(recipelist, this);
        }
        clear(v);
        createDialog(recipe, this).show();
    }

    public void addIngredient(View v){
        dfragment = dfragment.newInstance(baselist);
        dfragment.addRetrieveListener(new IngredientListDialog.RetrieveListener() {
            @Override
            public void onRetrieveListener(Ingredient e) {
                if(!(ingredientlist.contains(e))){
                    ingredientlist.add(e);
                    recycler.getAdapter().notifyDataSetChanged();
                    recipe.setIngredientList(ingredientlist);
                    recipe.setCost();
                    cost.setText("" + recipe.getCost());
                }
            }
        });
        dfragment.show(getFragmentManager(), "ingredient");
    }

    public ArrayList<Ingredient> getBaselist() {
        return baselist;
    }

    public void cancel(View v){
            dfragment.dismiss();
    }

    public void newIngredient(View v){
        dfragment.dismiss();
        NewIngredientDialog dialog = NewIngredientDialog.newInstance(baselist, ingredientlist);
        dialog.show(getFragmentManager(), "ingredient");
    }

    public void setInitialValues(Recipe e){
        name.setText(e.getName());
        cost.setText("" + e.getCost());
        quantity.setText("" + e.getQuantity());
        time.setText(e.timetoString(e.getTime()));
        metric.setSelection(super.getIndex(metric, e.getMetric()));
        ingredientlist = e.getIngredientList();
    }

    public AlertDialog createDialog(final Recipe recipe, final Context c){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("El registro ya existe")
                .setMessage("¿Quiere modificar " + recipe.getName() + "?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (Recipe e : recipelist) {
                            if(e.getName().equals(recipe.getName())){
                                recipelist.set(recipelist.indexOf(e), recipe);
                                RecipeJson.writeJson(recipelist, c);
                                break;
                            }
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        IngredientJson.writeJson(ingredientlist, c);
                    }
                });
        return builder.create();
    }

}
