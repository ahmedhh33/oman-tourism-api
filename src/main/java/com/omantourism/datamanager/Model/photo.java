package com.omantourism.datamanager.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class photo {
    public  String lable;
    @Id //should com from presistance
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  int id;
    public  String description;
    public  String path;

    public photo(String lable, int id, String description, String path) {
        this.lable = lable;
        this.id = id;
        this.description = description;
        this.path = path;
    }
}
