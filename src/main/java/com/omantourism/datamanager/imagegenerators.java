package com.omantourism.datamanager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api/v1/photos")
public class imagegenerators {
    CopyOnWriteArrayList<photo> photocolletion =new CopyOnWriteArrayList<>();

    @GetMapping
    public List<photo> getallphotos(){
        //photocolletion.add(new photo("we","1","forew","/photod"));
        return photocolletion;
    }

    @PostMapping
    public photo addphoto(@RequestBody photo incommingphoto){
        photocolletion.add(incommingphoto);
        return incommingphoto;

    }
    @GetMapping(path = "/{photoid}")
    public  photo getspiciphicphoto (@PathVariable String photoid) {
        photo resultphoto = null;
        Optional<photo> foundphoto = photocolletion.stream().filter((currphot) -> {
            return currphot.id.equals(photoid);
        }).findFirst();

        if (foundphoto.isPresent()) {

            resultphoto = foundphoto.get();
        }
    return resultphoto;
    }
    @PutMapping("/{photoid}")

    public photo updatephoto(@PathVariable String photoid,@RequestBody photo incommingphoto){
        photo foundphoto = getspiciphicphoto(photoid);

        foundphoto.lable=incommingphoto.lable;
        foundphoto.description=incommingphoto.description;
        foundphoto.path=incommingphoto.path;

        return foundphoto;
    }
    @DeleteMapping("/{photoid}")
    public ResponseEntity<String>  removephoto(@PathVariable String photoid){


        Optional<photo> foundphoto = photocolletion.stream().filter((currphot) -> {
            return currphot.id.equals(photoid);
        }).findFirst();

        if (foundphoto.isPresent()) {

              photocolletion.remove(foundphoto.get());
              return ResponseEntity.ok("Photo removed successfully");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
