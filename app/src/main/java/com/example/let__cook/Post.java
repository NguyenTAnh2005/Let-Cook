package com.example.let__cook;

public class Post {
    private int id_post;
    private String id_user, title,content,created_at,updated_at,p_status;

    public Post(int id_post, String id_user, String title, String content, String created_at, String updated_at, String p_status) {
        this.id_post = id_post;
        this.id_user = id_user;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.p_status = p_status;
    }

    public int getIdPost() { return id_post; }
    public void setIdPost(int id_post) { this.id_post = id_post; }

    public String getIdUser() { return id_user; }
    public void setIdUser(String id_user) { this.id_user = id_user; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getCreatedAt() { return created_at; }
    public void setCreatedAt(String created_at) { this.created_at = created_at; }

    public String getUpdatedAt() { return updated_at; }
    public void setUpdatedAt(String updated_at) { this.updated_at = updated_at; }

    public String getPStatus() { return p_status; }
    public void setPStatus(String p_status) { this.p_status = p_status; }
}
