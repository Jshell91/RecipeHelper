package jdev.recipehelper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dracc on 21/04/2016.
 */
public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{

    private ArrayList<Ingredient> data;

    public IngredientAdapter(ArrayList<Ingredient> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ingredient item = data.get(position);
        holder.setIngText(item);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView quantity;
        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.tvingname);
            quantity = (TextView) v.findViewById(R.id.tvquantity);
        }

        public void setIngText(Ingredient item){
            name.setText(item.getName());
            quantity.setText(Float.toString(item.getQuantity()));
        }
    }
}
