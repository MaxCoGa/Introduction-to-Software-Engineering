package com.uottawa.advancedui2;

/**
 * Created by cedric on 11/7/2017.
 */

public class Recette {
    private String name;
    private String state;
    private int imageId;
    private String imageName;
    private int calories;

    public Recette(String name, String state, int calories){

        this.name       = name;
        this.state      = state;
        this.calories   = calories;
    }

    public Recette(String nom, String paysOrigine, int calories, String imageName){
        this(nom, paysOrigine, calories);
        this.imageName = imageName;
    }

    public Recette(String nom, String paysOrigine,int calories, int id){
        this(nom, paysOrigine, calories);
        this.imageId = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getImage() {
        return imageId;
    }

    public void setImage(int id) {
        this.imageId = id;
    }

    public void setImageName(String name) { this.imageName = name;}

    public String getImageName(){return this.imageName;}

    public void setCalories(){this.calories = calories;}
    public int getCalories() {return this.calories;}
    public String toString(){
        return "Recette@{name: " + this.name +", image: "+ this.imageName +", state: " + this.state + ", calories: "+ this.calories + "}";
    }
}
