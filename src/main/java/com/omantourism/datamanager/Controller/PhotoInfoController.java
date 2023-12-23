package com.omantourism.datamanager.Controller;

import com.omantourism.datamanager.Model.photo;
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
        public List<photo> getallphotos(){

            return photoInfoServices.getallphotos();
        }

        @PostMapping
        public photo addphoto(@RequestBody photo incommingphoto){

            return photoInfoServices.addphoto(incommingphoto);

        }
        @GetMapping(path = "/{photoid}")
        public  photo getspiciphicphoto (@PathVariable String photoid) {

            return photoInfoServices.getspiciphicphoto(photoid);
        }
        @PutMapping("/{photoid}")
        public photo updatephoto(@PathVariable String photoid,@RequestBody photo incommingphoto){

            return photoInfoServices.updatephoto(photoid,incommingphoto);
        }
        @DeleteMapping("/{photoid}")
        public ResponseEntity<String> removephoto(@PathVariable String photoid){
        return photoInfoServices.removephoto(photoid);
        }
    }

