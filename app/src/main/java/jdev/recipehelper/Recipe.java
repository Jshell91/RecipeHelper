package jdev.recipehelper;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by dracc on 19/04/2016.
 */
public class Recipe implements Parcelable{

    // Creamos las variables a usar
    private String name;
    private Image photo;
    private String metric;
    private float quantity;
    private float price;
    private float cost;
    private int time;
    private ArrayList<Ingredient> ingredientList;

    private double costtime = 6.0;
    private double repay = 1;
    private double consumption = 0.5;

    public Recipe(){
        quantity = 0;
        cost = 0;
        price = 0;
        time = 0;
        ingredientList = new ArrayList<>();
    }

    // Constructor con variables necesarias
    public Recipe(String name, String metric, float quantity, float price, float cost, String time, ArrayList<Ingredient> ingredientList) {
        this.name = name;
		this.metric = metric;
        this.quantity = quantity;
        this.price = price;
        this.cost = cost;
        this.time = timetoInt(time);
        this.ingredientList = ingredientList;
    }

    public Recipe(String name) {
        this.name = name;
    }


    protected Recipe(Parcel in) {
        name = in.readString();
        metric = in.readString();
        quantity = in.readFloat();
        price = in.readFloat();
        cost = in.readFloat();
        time = in.readInt();
        ingredientList = in.createTypedArrayList(Ingredient.CREATOR);
        costtime = in.readDouble();
        repay = in.readDouble();
        consumption = in.readDouble();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    // Get necesarios para acceder a las variables.
    public String getName() {
        return name;
    }

    public Image getPhoto() {
        return photo;
    }

    public String getMetric() {
        return metric;
    }

    public float getQuantity() {
        return quantity;
    }

    public float getCost() {
        return cost;
    }

    public int getTime() {
        return time;
    }

    public float getPrice() {
        return price;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setCost(){
        float d = 0;
        for (Ingredient e : ingredientList) {
            d += e.getCost();
        }
        d += repay;
        d += ((time/3600000) * consumption);
        d += ((time/3600000) * costtime);
        this.cost = d;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setIngredientList(ArrayList<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public double getCosttime() {
        return costtime;
    }

    public double getRepay() {
        return repay;
    }

    public double getConsumption() {
        return consumption;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(metric);
        dest.writeFloat(quantity);
        dest.writeFloat(price);
        dest.writeFloat(cost);
        dest.writeInt(time);
        dest.writeTypedList(ingredientList);
        dest.writeDouble(costtime);
        dest.writeDouble(repay);
        dest.writeDouble(consumption);
    }

    public int timetoInt(String time){
        int f = 0;
        Log.d("TAG", "timetoInt: " + time);
        if(!time.isEmpty()){
            String[] s = time.split(":");
            f += (Float.parseFloat(s[0]) * 3600);
            f += (Float.parseFloat(s[1]) * 60);
            f += Float.parseFloat(s[2]);
        }
        return f;
    }

    public String timetoString(int f){
        String s;
        int f2 = f%60;
        int f3 = f2%60;
        s = f/60 + ":" + f2 + ":" + f3;
        Log.d("TAG",f + " timetoString: " + time);
        return s;
    }
}
