package com.example.let__cook;

public class User {
    private String id_user,first_name,last_name, email,password,birth_year,access;
    private Boolean gender;

    public User(String id, String f, String l, String birth,
                String email, String pass, String access,Boolean gd) {
        this.id_user = id;
        this.first_name = f;
        this.last_name = l;
        this.birth_year = birth;
        this.email = email;
        this.password = pass;
        this.access = access;
        this.gender=gd;
    }

    // Getters & Setters
    public String getIdUser() { return id_user; }
    public void setIdUser(String id) { this.id_user = id; }

    public String getFirstName() { return first_name; }
    public void setFirstName(String f) { this.first_name = f; }

    public String getLastName() { return last_name; }
    public void setLastName(String l) { this.last_name = l; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getBirthYear() { return birth_year; }
    public void setBirthYear(String birth_year) { this.birth_year = birth_year; }

    public String getAccess() { return access; }
    public void setAccess(String access) { this.access = access; }

    public Boolean getGender(){return gender;}
    public void setGender(boolean gd){this.gender=gd;}
}
