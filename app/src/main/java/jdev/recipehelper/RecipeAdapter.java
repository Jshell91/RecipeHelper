package jdev.recipehelper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
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
    public void onBindViewHolder(final RecipeHolder holder, final int position) {

        // Le damos a cada carta los datos que necesite
        holder.setCardText(dataset.get(position).getName());

        // Al hacer click en el boton de la calculadora nos lanzara a la correspondiente activity
        holder.btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Calculator.class);
                intent.putExtra("Recipe", dataset.get(position));
                v.getContext().startActivity(intent);
            }
        });

        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog dialog = createDialog(holder.getAdapterPosition(), holder.card.getContext());
                dialog.show();
                return false;
            }
        });
        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.card.getContext(), NewRecipe.class);
                intent.putParcelableArrayListExtra("RecipeList", dataset);
                intent.putExtra("Recipe", dataset.get(holder.getAdapterPosition()));
                holder.card.getContext().startActivity(intent);
            }
        });
    }

    public AlertDialog createDialog(final int position, final Context c){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Borrar Receta")
                .setMessage("¿Quiere eliminar " + dataset.get(position).getName() + "?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataset.remove(position);
                        RecipeJson.writeJson(dataset, c);
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
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
        public Button btnedit;
        public CardView card;

        // Constructor para inicializar los distintos componentes
        public RecipeHolder(View v){
            super(v);
            nombre = (TextView) v.findViewById(R.id.tvreccardname);
            btncalc = (Button) v.findViewById(R.id.btncalc);
            btnedit = (Button) v.findViewById(R.id.btnedit);
            card = (CardView) v.findViewById(R.id.recipecard);
        }

        // Set para modificar todos los campos de la carta a la vez
        public void setCardText(String name){
            nombre.setText(name);
        }
    }
}
