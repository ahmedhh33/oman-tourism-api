package com.omantourism.datamanager.Controller;

import com.omantourism.datamanager.Service.PhotoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/photoContent")
public class PhotoController {
@Autowired
    PhotoServices photoServices;
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getcontent(@PathVariable Integer id) throws IOException {

        return photoServices.getcontent(id);
    }


    @PostMapping()
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile file) {
       return photoServices.uploadImage(file);
    }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteImage(@PathVariable Integer id) {
           return photoServices.deleteImage(id);
        }
    @PutMapping("/{id}")
    public ResponseEntity<String> putImage(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        return photoServices.putImage(id,file);
    }

    }
