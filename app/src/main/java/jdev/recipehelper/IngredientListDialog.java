package jdev.recipehelper;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jshell on 16/06/2016.
 */

public class IngredientListDialog extends DialogFragment{
    RecyclerView recycler;
    IngredientListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        JsonObject jsonObject = null;
        ArrayList<Ingredient> ingredientList;


        View v = inflater.inflate(R.layout.dialoglist, container, false);
        recycler = (RecyclerView) v.findViewById(R.id.recyclerdialog);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        try {
            jsonObject = IngredientJson.readJson(getActivity());

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(jsonObject != null) {
            ingredientList = IngredientJson.toArrayList(jsonObject.getAsJsonArray("ingredientList"));
            adapter = new IngredientListAdapter(ingredientList);
            recycler.setAdapter(adapter);
            return v;
        }
        return null;
    }
}
