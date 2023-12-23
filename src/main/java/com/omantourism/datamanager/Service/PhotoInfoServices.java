package com.omantourism.datamanager.Service;

import com.omantourism.datamanager.Model.photo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class PhotoInfoServices {
    CopyOnWriteArrayList<photo> photocolletion =new CopyOnWriteArrayList<>();


    public List<photo> getallphotos(){
        //photocolletion.add(new photo("we","1","forew","/photod"));
        return photocolletion;
    }


    public photo addphoto(photo incommingphoto){
        photocolletion.add(incommingphoto);
        return incommingphoto;

    }

    public  photo getspiciphicphoto (String photoid) {
        photo resultphoto = null;
        Optional<photo> foundphoto = photocolletion.stream().filter((currphot) -> {
            return currphot.id.equals(photoid);
        }).findFirst();

        if (foundphoto.isPresent()) {

            resultphoto = foundphoto.get();
        }
    return resultphoto;
    }


    public photo updatephoto( String photoid,photo incommingphoto){
        photo foundphoto = getspiciphicphoto(photoid);

        foundphoto.lable=incommingphoto.lable;
        foundphoto.description=incommingphoto.description;
        foundphoto.path=incommingphoto.path;

        return foundphoto;
    }

    public ResponseEntity<String>  removephoto( String photoid){


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
