package jdev.recipehelper;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by dracc on 15/04/2016.
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{
    private String[] dataset;

    public RecipeAdapter(String[] data){
        dataset = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setCardText(dataset[position]);
        holder.btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Calculator.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public void OpenCalc(View v){
        Intent intent = new Intent(v.getContext(), Calculator.class);
        v.getContext().startActivity(intent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public Button btncalc;
        public ViewHolder(View v){
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre);
            btncalc = (Button) v.findViewById(R.id.button2);
        }

        public void setCardText(String name){
            nombre.setText(name);
        }
    }


}
