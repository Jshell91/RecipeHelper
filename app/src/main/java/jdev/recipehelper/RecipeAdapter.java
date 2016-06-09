package jdev.recipehelper;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dracc on 15/04/2016.
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder>{
    // Adapter custom para las diferentes cartas que nos muestran las recetas.

    // Dataset a usar
    private ArrayList<Recipe> dataset;

    // Constructor que define el dataset
    public RecipeAdapter(ArrayList<Recipe> data){
        dataset = data;
    }

    // Creamos el IngredientHolder con el layout seleccionado
    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, parent, false);
        return new RecipeHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, final int position) {

        // Le damos a cada carta los datos que necesite
        holder.setCardText(dataset.get(position).getName());

        // Al hacer click en el boton de la calculadora nos lanzara a la correspondiente activity
        holder.btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Calculator.class);
                intent.putExtra("Nombre", dataset.get(position).getName());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    // IngredientHolder personalizado con el que manejaremos los elementos necesarios del layout
    public static class RecipeHolder extends RecyclerView.ViewHolder {
        // Componentes a los que accederemos.
        public TextView nombre;
        public Button btncalc;

        // Constructor para inicializar los distintos componentes
        public RecipeHolder(View v){
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre);
            btncalc = (Button) v.findViewById(R.id.button2);
        }

        // Set para modificar todos los campos de la carta a la vez
        public void setCardText(String name){
            nombre.setText(name);
        }
    }


}
