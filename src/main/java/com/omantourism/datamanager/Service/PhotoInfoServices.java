package com.omantourism.datamanager.Service;

import com.omantourism.datamanager.Model.Photo;
import com.omantourism.datamanager.Model.PhotowithType;
import com.omantourism.datamanager.repository.PhotoRepository;
import com.omantourism.datamanager.repository.PhotoTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhotoInfoServices {
    //CopyOnWriteArrayList<photo> photocolletion =new CopyOnWriteArrayList<>();
    @Autowired
    public PhotoRepository photoRepository;
    @Autowired
    public PhotoTypeRepository photoTypeRepository;
    public List<Photo> getallphotos(){
        //photocolletion.add(new photo("we","1","forew","/photod"));
        //return photocolletion;
        return photoRepository.findAll();
    }


    public Photo addphoto(Photo incommingphoto){
        //photocolletion.add(incommingphoto);
        photoRepository.save(incommingphoto);
        return incommingphoto;

    }

    public Photo getspiciphicphoto (Integer photoid) {

    Optional<Photo> optionalPhoto = photoRepository.findById(photoid);
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

    public Photo updatephotoS(Integer photoid, Photo incommingphoto){
        Photo foundphoto = getspiciphicphoto(photoid);
        foundphoto.lable=incommingphoto.lable;
        foundphoto.description=incommingphoto.description;
        foundphoto.path=incommingphoto.path;
        photoRepository.save(foundphoto);
        return foundphoto;
    }





    public Photo updatephoto(Integer photoid, @RequestBody PhotowithType photowithType){
        Photo foundphoto = getspiciphicphoto(photoid);
        foundphoto.lable=photowithType.photo.lable;
        foundphoto.description=photowithType.photo.description;
        //foundphoto.photoType = photoTypeRepository.findById(photowithType.photoTypeId).get();
        foundphoto.photoTypes = photoTypeRepository.findAll().stream().filter((currentphoto) ->
        {
            return photowithType.photoTypeId.contains(currentphoto.id);
        }).collect(Collectors.toSet());


        return photoRepository.save(foundphoto);
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

        Optional<Photo> optionalPhoto = photoRepository.findById(photoid);

        if (optionalPhoto.isPresent()) {
            photoRepository.delete(optionalPhoto.get());
            return ResponseEntity.ok("Photo removed successfully");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found with ID: " + photoid);
        }
    }
}
