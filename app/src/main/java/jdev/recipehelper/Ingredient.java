package jdev.recipehelper;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by dracc on 19/04/2016.
 */
public class Ingredient implements Parcelable{

    // Creamos las variables que usara.
    private String name;
    private String type;
    private String state;
    private String metric;
    private String lot;
    private float quantity;
    private double cost;
    private String expiration;

    // Constructor basico con nombre y cantidad, solo de prueba el momento.
    public Ingredient(String name, float quantity, double cost) {
        this(name, quantity, cost, "g");
    }

    public Ingredient(String name, float quantity, double cost, String metric) {
        this(name, name, "Cerrado", metric, "N/A", quantity, cost, "20/12/2020");
    }

    public Ingredient(String name, String type, String state, String metric, String lot, float quantity, double cost, String expiration) {
        this.name = name;
        this.type = type;
        this.state = state;
        this.metric = metric;
        this.lot = lot;
        this.quantity = quantity;
        this.cost = cost;
        this.expiration = expiration;
    }

    protected Ingredient(Parcel in) {
        name = in.readString();
        type = in.readString();
        state = in.readString();
        metric = in.readString();
        lot = in.readString();
        quantity = in.readFloat();
        cost = in.readDouble();
        expiration = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    // Get necesarios para acceder a las variables.
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getState() {
        return state;
    }

    public String getMetric() {
        return metric;
    }

    public String getLot() {
        return lot;
    }

    public float getQuantity() {
        return quantity;
    }

    public double getCost() {
        return cost;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(state);
        dest.writeString(metric);
        dest.writeString(lot);
        dest.writeFloat(quantity);
        dest.writeDouble(cost);
        dest.writeString(expiration);
    }

    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(!(o instanceof Ingredient))
            return false;
        Ingredient e = (Ingredient) o;
        return e.getName().equals(getName());
    }
}
