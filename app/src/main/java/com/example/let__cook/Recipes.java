package com.example.let__cook;

public class Recipes {
    private String id_food,step;
    private int id_recipes;
    public Recipes(int id_re, String id_fo,String st){
        this.id_recipes=id_re;
        this.id_food=id_fo;
        this.step=st;
    }

    //Get Set
    public int getIdRecipes(){return id_recipes;}
    public String getIdFood(){return id_food;}
    public String getStep(){return step;}

    public void setIdRecipes(int id_re){this.id_recipes=id_re;}
    public void setIdFood(String id_fo){this.id_food=id_fo;}
    public void setStep(String st){this.step=st;}
}
