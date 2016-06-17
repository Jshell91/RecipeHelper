package jdev.recipehelper;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class NewRecipe extends BaseActivity {

    ArrayList<Ingredient> ingredientlist = new ArrayList<Ingredient>();
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newrecipe);
        recycler = (RecyclerView) findViewById(R.id.recyclernewrecipe);

        // Creamos el layoutmanager y lo asignamos al recyclerview
        RecyclerView.LayoutManager manager;
        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);

        RecyclerView.Adapter adapter = new IngredientListAdapter(ingredientlist);
        recycler.setAdapter(adapter);
    }

    public void clear(View v){

    }

    public void saveRecipe(View v){

    }

    public void addIngredient(View v){
        DialogFragment dfragment = new IngredientListDialog();
        dfragment.show(getFragmentManager(), "ingredient");
    }
}
