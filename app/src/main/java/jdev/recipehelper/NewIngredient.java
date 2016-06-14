package jdev.recipehelper;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class NewIngredient extends BaseActivity {

    Spinner spmetric;
    EditText etningname, etningtype, etningcost, etningquantity, etninglot, etningdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newingredient);

        etningname = (EditText) findViewById(R.id.etningname);
        etningtype = (EditText) findViewById(R.id.etningtype);
        etningcost = (EditText) findViewById(R.id.etningcost);
        etningquantity = (EditText) findViewById(R.id.etningquantity);
        etninglot = (EditText) findViewById(R.id.etninglot);
        etningdate = (EditText) findViewById(R.id.etningdate);
        spmetric = (Spinner) findViewById(R.id.spinnermetric);
    }

    public void clearIng(View v){

    }

    public void saveIngredient(View v){
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("name", etningname.getText().toString());
        ingredient.addProperty("type", etningtype.getText().toString());
        ingredient.addProperty("cost", etningcost.getText().toString());
        ingredient.addProperty("quantity", etningquantity.getText().toString());
        ingredient.addProperty("metric", spmetric.getSelectedItem().toString());
        ingredient.addProperty("lot", etninglot.getText().toString());
        ingredient.addProperty("date", etningdate.getText().toString());
        Log.d("TAG", "saveIngredient: " + ingredient.toString());



        try {

            FileReader freader = new FileReader(this.getFilesDir().getPath() + "/ingredientList.json");
            JsonReader jreader = new JsonReader(freader);
            jreader.setLenient(true);
            JsonParser parser = new JsonParser();
            JsonElement jelement = parser.parse(jreader);
            freader.close();
            jelement.getAsJsonObject().getAsJsonArray("ingredientListajo").add(ingredient);

            FileOutputStream output = openFileOutput("ingredientList.json", Context.MODE_PRIVATE);
            output.write(jelement.toString().getBytes());
            output.close();
            Log.d("TAG", "saveIngredient: " + "hecho");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
