package com.omantourism.datamanager.Controller;

import com.omantourism.datamanager.Model.photo;
import com.omantourism.datamanager.Service.PhotoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
@RestController
@RequestMapping("/api/v1/photos")
public class PhotoController {

@Autowired
    PhotoServices photoServices;

        @GetMapping
        public List<photo> getallphotos(){

            return photoServices.getallphotos();
        }

        @PostMapping
        public photo addphoto(@RequestBody photo incommingphoto){

            return photoServices.addphoto(incommingphoto);

        }
        @GetMapping(path = "/{photoid}")
        public  photo getspiciphicphoto (@PathVariable String photoid) {

            return photoServices.getspiciphicphoto(photoid);
        }
        @PutMapping("/{photoid}")
        public photo updatephoto(@PathVariable String photoid,@RequestBody photo incommingphoto){

            return photoServices.updatephoto(photoid,incommingphoto);
        }
        @DeleteMapping("/{photoid}")
        public ResponseEntity<String> removephoto(@PathVariable String photoid){
        return photoServices.removephoto(photoid);
        }
    }

