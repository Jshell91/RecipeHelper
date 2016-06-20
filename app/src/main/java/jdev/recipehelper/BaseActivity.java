package jdev.recipehelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

/**
 * Created by Jshell on 31/05/2016.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        Intent intent;
        //loquesea
        switch(item.getItemId()){
            case R.id.home:
                intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                return true;
            case R.id.ingredient:
                intent = new Intent(this, IngredientList.class);
                this.startActivity(intent);
                return true;
            case R.id.newrecipe:
                intent = new Intent(this, NewRecipe.class);
                this.startActivity(intent);
                return true;
        }

        return false;
    }

    protected int getIndex(Spinner spinner, String value){
        int index = 0;
        for (int i = 0; i<spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)){
                index = i;
                break;
            }
        }
        return index;
    }
}
