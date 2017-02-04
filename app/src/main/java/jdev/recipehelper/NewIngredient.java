package jdev.recipehelper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class NewIngredient extends BaseActivity {

    Spinner spmetric, spstate;
    EditText etningname, etningtype, etningcost, etningquantity, etninglot, etningdate;
    ArrayList<Ingredient> ingredientlist;
    Ingredient baseIngredient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newingredient);

        etningname = (EditText) findViewById(R.id.etningname);
        etningtype = (EditText) findViewById(R.id.etningtype);
        etningcost = (EditText) findViewById(R.id.etningcost);
        etningquantity = (EditText) findViewById(R.id.etningquantity);
        etninglot = (EditText) findViewById(R.id.etninglot);
        etningdate = (EditText) findViewById(R.id.etningdate);
        spmetric = (Spinner) findViewById(R.id.spinnermetric);
        spstate = (Spinner) findViewById(R.id.spinnerstate);

        ingredientlist = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            ingredientlist = bundle.getParcelableArrayList("IngredientList");
            baseIngredient = bundle.getParcelable("Ingredient");
            if(baseIngredient != null){setInitialValues(baseIngredient);}
        }
    }

    public void clearIng(View v){
        etningname.setText(null);
        etningtype.setText(null);
        etningcost.setText(null);
        etningquantity.setText(null);
        etninglot.setText(null);
        etningdate.setText(null);
        spmetric.setSelection(0);
        spstate.setSelection(0);
    }

    public void saveIngredient(View v){        
        final Ingredient ingredient = new Ingredient(etningname.getText().toString(), etningtype.getText().toString(),
                spstate.getSelectedItem().toString(), spmetric.getSelectedItem().toString(),
                etninglot.getText().toString(), Float.parseFloat(etningquantity.getText().toString()),
                Double.parseDouble(etningcost.getText().toString()), etningdate.getText().toString());
        Log.d("TAG", "saveIngredient: " + ingredient.toString());
        if(!(ingredientlist.contains(ingredient))) {
            ingredientlist.add(ingredient);
            IngredientJson.writeJson(ingredientlist, this);
        }else{
            AlertDialog dialog = createDialog(ingredient, this);
            dialog.show();
        }
        clearIng(v);
    }

    public void setInitialValues(Ingredient e){
        etningname.setText(e.getName());
        etningtype.setText(e.getType());
        etningcost.setText("" + e.getCost());
        etningquantity.setText("" + e.getQuantity());
        etninglot.setText(e.getLot());
        etningdate.setText(e.getExpiration());
        spmetric.setSelection(getIndex(spmetric,e.getMetric()));
        spstate.setSelection(getIndex(spstate, e.getState()));
    }

    public AlertDialog createDialog(final Ingredient ingredient, final Context c){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("El registro ya existe")
                .setMessage("¿Quiere modificar " + baseIngredient.getName() + "?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (Ingredient e : ingredientlist) {
                            if(e.getName().equals(baseIngredient.getName())){
                                ingredientlist.set(ingredientlist.indexOf(e), ingredient);
                                IngredientJson.writeJson(ingredientlist, c);
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
