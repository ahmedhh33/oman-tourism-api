package com.omantourism.datamanager.Service;

import com.omantourism.datamanager.Model.photo;
import com.omantourism.datamanager.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class PhotoInfoServices {
    //CopyOnWriteArrayList<photo> photocolletion =new CopyOnWriteArrayList<>();
    @Autowired
    public PhotoRepository photoRepository;
    public List<photo> getallphotos(){
        //photocolletion.add(new photo("we","1","forew","/photod"));
        //return photocolletion;
        return photoRepository.findAll();
    }


    public photo addphoto(photo incommingphoto){
        //photocolletion.add(incommingphoto);
        photoRepository.save(incommingphoto);
        return incommingphoto;

    }

    public  photo getspiciphicphoto (Integer photoid) {

    Optional<photo> optionalPhoto = photoRepository.findById(photoid);
//        photo resultphoto = null;
//        Optional<photo> foundphoto = photocolletion.stream().filter((currphot) -> {
//            return currphot.id.equals(photoid);
//        }).findFirst();
//
//        if (foundphoto.isPresent()) {
//
//            resultphoto = foundphoto.get();
//        }
//    return resultphoto;

        return optionalPhoto.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found with ID: " + photoid)
        );
    }


    public photo updatephoto( Integer photoid,photo incommingphoto){
        photo foundphoto = getspiciphicphoto(photoid);
        foundphoto.lable=incommingphoto.lable;
        foundphoto.description=incommingphoto.description;
        foundphoto.path=incommingphoto.path;
        photoRepository.save(incommingphoto);
        return foundphoto;
    }

    public ResponseEntity<String>  removephoto( Integer photoid){


//        Optional<photo> foundphoto = photocolletion.stream().filter((currphot) -> {
//            return currphot.id.equals(photoid);
//        }).findFirst();
//
//        if (foundphoto.isPresent()) {
//
//              photocolletion.remove(foundphoto.get());
//              return ResponseEntity.ok("Photoinfo removed successfully");
//        }
//        else {
//            return ResponseEntity.notFound().build();
//        }

        Optional<photo> optionalPhoto = photoRepository.findById(photoid);

        if (optionalPhoto.isPresent()) {
            photoRepository.delete(optionalPhoto.get());
            return ResponseEntity.ok("Photo removed successfully");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found with ID: " + photoid);
        }
    }
}
