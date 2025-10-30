package com.example.let__cook;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;

public class Food implements Serializable {
    private String id_food,name_food,img_food,desc_food,link_food,note_food,tag_food;
    double difficulty;
    public Food(String id,String name,double dif,String img,String desc,String link,String note,String tag){
        id_food=id;name_food=name;img_food=img;difficulty=dif;desc_food=desc;link_food=link;note_food=note;tag_food=tag;
    }

    // Getters & Setters
    public String getId_food() { return id_food; }
    public String getName_food() { return name_food; }
    public double getDifficulty() { return difficulty; }
    public String getImg_food() { return img_food; }
    public String getDesc_food() { return desc_food; }
    public String getLink_cook() { return link_food; }
    public String getNote_food() { return note_food; }
    public String getTag_food() { return tag_food; }


    public void setId_food(String id) { this.id_food = id; }
    public void setName_food(String name) { this.name_food = name; }
    public void setDifficulty(double dif) { this.difficulty = dif; }
    public void setImg_food(String img) { this.img_food = img; }
    public void setDesc_food(String desc) { this.desc_food = desc; }
    public void setLink_cook(String link) { this.link_food = link; }
    public void setNote_food(String note) { this.note_food = note; }
    public void setTag_food(String tag) { this.tag_food = tag; }

    // Trả về resource ID của ảnh (từ drawable)
    public int getImgResId(Context context) {
        String imgName = img_food;
        if (imgName.endsWith(".png")) {
            imgName = imgName.replace(".png", "");
        }
        return context.getResources().getIdentifier(imgName, "drawable", context.getPackageName());
    }
}
