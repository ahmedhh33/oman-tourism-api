package com.omantourism.datamanager.Controller;

import com.omantourism.datamanager.Service.PhotoServices;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
       return photoServices.uploadImage(file,id);
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
