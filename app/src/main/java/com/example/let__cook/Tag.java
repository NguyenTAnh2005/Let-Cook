package com.example.let__cook;

public class Tag {
    private String id_tag;
    private String name_tag;
    private String desc_tag;

    public Tag(String id_tag, String name_tag, String desc_tag) {
        this.id_tag = id_tag;
        this.name_tag = name_tag;
        this.desc_tag = desc_tag;
    }

    public String getId_tag() { return id_tag; }
    public void setId_tag(String id_tag) { this.id_tag = id_tag; }

    public String getName_tag() { return name_tag; }
    public void setName_tag(String nameTag) { this.name_tag = nameTag; }

    public String getDesc_tag() { return desc_tag; }
    public void setDesc_tag(String desc_tag) { this.desc_tag = desc_tag; }
}
