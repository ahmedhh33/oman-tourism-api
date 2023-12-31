package com.omantourism.datamanager.Controller;

import com.omantourism.datamanager.Model.PhotoType;
import com.omantourism.datamanager.Service.PhotoTypeServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/photoTypes")
public class PhotoTypeController {
    @Autowired
    PhotoTypeServices photoTypeServices;
    @GetMapping
    public List<PhotoType> GetAllPhotoType(){
        return photoTypeServices.GetAllPhotoType();
    }
    @GetMapping("/{id}")
    public PhotoType GetPhotoTypeById(@PathVariable Integer id){
        return photoTypeServices.GetPhotoTypeById(id);
    }
    @PostMapping
    public PhotoType AddPhotoType(@Valid @RequestBody PhotoType incommingphototype){
        return photoTypeServices.AddPhotoType(incommingphototype);
    }
    @PutMapping("/{id}")
    public PhotoType UpdatePhotoType(@PathVariable Integer id,@RequestBody PhotoType incommingphototype){

        return photoTypeServices.UpdatePhotoType(id,incommingphototype);
    }
    @DeleteMapping("/{id}")
    public PhotoType DeleteType(@PathVariable Integer id){
        return photoTypeServices.DeleteType(id);
    }
}
