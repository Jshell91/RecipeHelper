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
 * Created by dracc on 21/04/2016.
 */
public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientHolder>{
    // Adapter personalizado que usaremos para mostrar los ingredientes en la calculadora, por el momento no funciona.

    // Instanciamos la activity para poder acceder a sus metodos.
    private Calculator calc;
    // Dataset que usara.
    private ArrayList<Ingredient> data;


    // Constructor que define el dataset
    public IngredientAdapter(ArrayList<Ingredient> data) {
        this.data = data;
    }

    // Constructor con dataset y activity
    public IngredientAdapter(Calculator calc, ArrayList<Ingredient> data) {
        this.calc = calc;
        this.data = data;
    }

    // Creamos el IngredientHolder con el layout seleccionado
    @Override
    public IngredientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_card, parent, false);
        return new IngredientHolder(v);
    }

    @Override
    public void onBindViewHolder(final IngredientHolder holder, final int position) {
        Ingredient item = data.get(position);
        holder.setIngText(item);
        holder.quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
        holder.quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setQuantities(position, data, holder);
            }
        });
    }


    // Importante pedirle a este metodo que nos devuelva el tamaño del array, o no creara ni una card
    @Override
    public int getItemCount() {
        return data.size();
    }

    // Con este metodo modificamos la cantidad del resto de ingredientes y actualizamos el coste total.
    private void setQuantities(int position, ArrayList<Ingredient> data, IngredientHolder holder){
        float qprev = data.get(position).getQuantity();
        float qnext;
        float mult;

        try{
            qnext = Float.parseFloat(holder.quantity.getText().toString());
        }catch(NumberFormatException ex){
            qnext = 1;
        }
        if (qnext == 0){
            qnext = 1;
        }
        mult = qnext/qprev;
        for (int i = 0; i<data.size(); i++){
            Ingredient e = data.get(i);
            e.setQuantity(e.getQuantity()*mult);
            Log.d("TAG", e.getName() + " " + e.getQuantity());
            data.set(i, e);
            try{
                notifyItemChanged(i);
            }catch (Exception ex){
                Log.d("peta", "setQuantities: " + ex);
            }
        }
        Log.d("TAG","mult: " + mult +" Siguiente");

        calc.setCostText(data);
    }

    // IngredientHolder personalizado con el que manejaremos los elementos necesarios del layout
    public static class IngredientHolder extends RecyclerView.ViewHolder{
        // Componentes a los que accederemos.
        TextView name;
        EditText quantity;

        // Constructor para inicializar los distintos componentes
        public IngredientHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.tvrecname);
            quantity = (EditText) v.findViewById(R.id.etingquantity);
        }

        // Set para modificar todos los campos de la carta a la vez
        public void setIngText(Ingredient item){
            name.setText(item.getName());
            quantity.setText(Float.toString(item.getQuantity()));
        }

        public void setQuantityText(Ingredient e) {
            this.quantity.setText("" + e.getQuantity());
        }
    }
}
