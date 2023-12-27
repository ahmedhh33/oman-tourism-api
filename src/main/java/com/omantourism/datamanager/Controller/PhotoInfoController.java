package com.omantourism.datamanager.Controller;

import com.omantourism.datamanager.Model.Photo;
import com.omantourism.datamanager.Model.PhotowithType;
import com.omantourism.datamanager.Service.PhotoInfoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/photos")
public class PhotoInfoController {

@Autowired
PhotoInfoServices photoInfoServices;

        @GetMapping
        public List<Photo> getallphotos(){

            return photoInfoServices.getallphotos();
        }

        @PostMapping
        public Photo addphoto(@RequestBody Photo incommingphoto){
            return photoInfoServices.addphoto(incommingphoto);

        }
        @GetMapping(path = "/{photoid}")
        public Photo getspiciphicphoto (@PathVariable Integer photoid) {

            return photoInfoServices.getspiciphicphoto(photoid);
        }
        @PutMapping("/{photoid}")
        public Photo updatephoto(@PathVariable Integer photoid, @RequestBody PhotowithType photowithType){
            return photoInfoServices.updatephoto(photoid,photowithType);
        }
        @DeleteMapping("/{photoid}")
        public ResponseEntity<String> removephoto(@PathVariable Integer photoid){
        return photoInfoServices.removephoto(photoid);
        }
    }

