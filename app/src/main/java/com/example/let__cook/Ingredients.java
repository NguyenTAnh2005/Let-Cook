package com.example.let__cook;

public class Ingredients {
    private String id_food,desc_ingredients;
    private int id_ingredients;
    public Ingredients(int id_in,String id_fo, String desc){
        this.id_ingredients=id_in;
        this.id_food=id_fo;
        this.desc_ingredients=desc;

    }
    // Get SET
    public int getIdIngredients(){return id_ingredients;}
    public String getIdFood(){return id_food;}
    public String getDescIngredients(){return desc_ingredients;}

    public void setIdIngredients(int id_in){this.id_ingredients=id_in;}
    public void setIdFood(String id_fo){this.id_food=id_fo;}
    public void setDescIngredients(String desc){this.desc_ingredients=desc;}

}
