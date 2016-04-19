package jdev.recipehelper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by dracc on 15/04/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private String[] dataset;

    public MyAdapter(String[] data){
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

    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public ViewHolder(View v){
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre);
        }

        public void setCardText(String name){
            nombre.setText(name);
        }
    }


}
