package com.omantourism.datamanager.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PhotoType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public  String Type;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
