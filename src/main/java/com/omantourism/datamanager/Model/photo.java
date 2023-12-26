package com.omantourism.datamanager.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class photo {
    public  String lable;
    @Id //should come from presistance
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  int id;
    public  String description;
    public  String path;

    public photo() {
    }

    public photo(String lable, String description){
        this.lable = lable;
        this.description = description;
    }
    public photo(String lable, String description, String path) {
        this.lable = lable;
        this.description = description;
        this.path = path;
    }

    public int getId() {
        return id;
    }
    public void setPath(String path) {
        this.path = path;
    }

}
