package jdev.recipehelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IngredientList extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);
    }

    public void newIngredient(View v){
        Intent intent = new Intent(this, NewIngredient.class);
        this.startActivity(intent);
    }
}
