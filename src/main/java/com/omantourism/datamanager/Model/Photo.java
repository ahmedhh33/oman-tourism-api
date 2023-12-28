package com.omantourism.datamanager.Model;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.util.Set;

@Entity
public class Photo {
    public  String lable;
    @Id //should come from presistance
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  int id;
    public  String description;
    public  String path;

    public Photo() {
    }

    public Photo(String lable, String description){
        this.lable = lable;
        this.description = description;
    }
    public Photo(String lable, String description, String path) {
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

    //@OneToOne(cascade = CascadeType.PERSIST)
    //@JoinColumn(name = "PHOTO_TYPE_ID",referencedColumnName = "id")

    @ManyToMany
    @JoinTable(name = "photoinfo_phototype_type",
    joinColumns = @JoinColumn(name = "photoinfo_id"),
    inverseJoinColumns = @JoinColumn(name = "phototype_id"))
    public Set<PhotoType> photoTypes;

}
