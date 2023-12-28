package com.omantourism.datamanager.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class PhotoType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PhotoType() {
    }

    public PhotoType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    //@OneToOne(mappedBy = "photoType")
    @ManyToMany(mappedBy = "photoTypes")
    @JsonIgnore
    public Set<Photo> photo;
}
