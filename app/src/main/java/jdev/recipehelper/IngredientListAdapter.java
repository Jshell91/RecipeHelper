package jdev.recipehelper;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jshell on 15/06/2016.
 */

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientListHolder> {
    // Adapter personalizado que usaremos para mostrar los ingredientes en el listado.

    // Instanciamos la activity para poder acceder a sus metodos.
    private Calculator calc;
    // Dataset que usara.
    private ArrayList<Ingredient> data;


    // Constructor que define el dataset
    public IngredientListAdapter(ArrayList<Ingredient> data) {
        this.data = data;
    }

    // Creamos el IngredientHolder con el layout seleccionado
    @Override
    public IngredientListAdapter.IngredientListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredientlist_card, parent, false);
        return new IngredientListAdapter.IngredientListHolder(v);
    }


    @Override
    public void onBindViewHolder(IngredientListHolder holder, final int position) {
        Ingredient item = data.get(position);
        holder.setIngText(item);
    }


    // Importante pedirle a este metodo que nos devuelva el tamaño del array, o no creara ni una card
    @Override
    public int getItemCount() {
        return data.size();
    }



    // IngredientHolder personalizado con el que manejaremos los elementos necesarios del layout
    public static class IngredientListHolder extends RecyclerView.ViewHolder{
        // Componentes a los que accederemos.
        Ingredient ing;
        TextView name;
        TextView quantity;
        TextView cost;
        TextView metric;

        // Constructor para inicializar los distintos componentes
        public IngredientListHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.tvinglistname);
            quantity = (TextView) v.findViewById(R.id.tvinglistquantity);
            cost = (TextView) v.findViewById(R.id.tvinglistcost);
            metric = (TextView) v.findViewById(R.id.tvinglistmetric);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        // Set para modificar todos los campos de la carta a la vez
        public void setIngText(Ingredient item){
            ing = item;
            name.setText(item.getName());
            quantity.setText(Float.toString(item.getQuantity()));
            cost.setText(Double.toString(item.getCost()));
            metric.setText(item.getMetric());
        }

        public void setQuantityText(Ingredient e) {
            this.quantity.setText("" + e.getQuantity());
        }


    }
}
