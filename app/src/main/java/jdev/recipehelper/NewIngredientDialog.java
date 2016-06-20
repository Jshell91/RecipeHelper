package jdev.recipehelper;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jshell on 19/06/2016.
 */

public class NewIngredientDialog extends DialogFragment {

    EditText name, quantity, cost;
    Spinner metric;
    ArrayList<Ingredient> baselist = new ArrayList<>();

    public static NewIngredientDialog newInstance(ArrayList<Ingredient> elist, ArrayList<Ingredient> ilist){
        NewIngredientDialog d = new NewIngredientDialog();
        Bundle args = new Bundle();
        args.putParcelableArrayList("BaseList", elist);
        args.putParcelableArrayList("IngredientList", ilist);
        d.setArguments(args);
        return d;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_newingredient, container, false);
        name = (EditText) v.findViewById(R.id.etdnewname);
        quantity = (EditText) v.findViewById(R.id.etdnewquantity);
        cost = (EditText) v.findViewById(R.id.etdnewcost);
        metric = (Spinner) v.findViewById(R.id.spinnerdnewmetric);
        Button accept = (Button) v.findViewById(R.id.btndnewok);
        Button cancel = (Button) v.findViewById(R.id.btndnewcancel);
        final ArrayList<Ingredient> ingredientList = getArguments().getParcelableArrayList("IngredientList");
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ingredient e = new Ingredient(name.getText().toString(),
                        Float.parseFloat(quantity.getText().toString()),
                        Double.parseDouble(cost.getText().toString()),
                        metric.getSelectedItem().toString());
                if(!baselist.contains(e)){
                    baselist.add(e);
                    if(!ingredientList.contains(e)){
                        ingredientList.add(e);
                    }
                    IngredientJson.writeJson(baselist, getActivity());
                }else{
                    Toast.makeText(getActivity(), "Ese ingrediente ya esta dado de alta. ", Toast.LENGTH_SHORT).show();
                }
                getDialog().dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();

            }
        });
        baselist = getArguments().getParcelableArrayList("BaseList");
        return v;
    }
}
