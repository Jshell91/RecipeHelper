package jdev.recipehelper;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Jshell on 16/06/2016.
 */

public class IngredientListDialog extends DialogFragment{
    RecyclerView recycler;
    IngredientListAdapter adapter;
    RetrieveListener retrieve;
    ArrayList<Ingredient> ingredientList = new ArrayList<>();

    public static IngredientListDialog newInstance(ArrayList<Ingredient> elist){
        IngredientListDialog d = new IngredientListDialog();
        Bundle args = new Bundle();
        args.putParcelableArrayList("IngredientList", elist);
        d.setArguments(args);
        return d;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dialoglist, container, false);
        recycler = (RecyclerView) v.findViewById(R.id.recyclerdialog);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        Bundle args = getArguments();
        ingredientList = args.getParcelableArrayList("IngredientList");
        adapter = new IngredientListAdapter(ingredientList, this.getActivity());
        recycler.setAdapter(adapter);
        return v;
    }

    public void addRetrieveListener(RetrieveListener retrieve){
        this.retrieve = retrieve;
    }

    public interface RetrieveListener{
        void onRetrieveListener(Ingredient e);
    }
}
