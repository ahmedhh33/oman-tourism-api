package com.omantourism.datamanager.Service;

import com.omantourism.datamanager.Model.PhotoType;
import com.omantourism.datamanager.repository.PhotoTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoTypeServices {
    @Autowired
    PhotoTypeRepository photoTypeRepository;

    public List<PhotoType> GetAllPhotoType(){
        return photoTypeRepository.findAll();
    }

    public PhotoType GetPhotoTypeById(Integer id){
        Optional<PhotoType> types = photoTypeRepository.findById(id);
        return types.orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found with ID: " + id));
    }

    public PhotoType AddPhotoType(PhotoType incommingphototype){
        photoTypeRepository.save(incommingphototype);
        return incommingphototype;
    }

    public PhotoType UpdatePhotoType(Integer id, PhotoType incommingphototype) {
        PhotoType gettype = GetPhotoTypeById(id);

        if (gettype != null) {
            gettype.setType(incommingphototype.getType());
            photoTypeRepository.save(gettype);

            return gettype;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"PhotoType not found with ID: "+id);
        }
    }


    public PhotoType DeleteType(Integer id){
        PhotoType gettype = GetPhotoTypeById(id);
        photoTypeRepository.delete(gettype);
        return null;
    }
}
