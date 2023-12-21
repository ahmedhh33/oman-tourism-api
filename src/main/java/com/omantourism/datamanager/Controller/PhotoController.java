package com.omantourism.datamanager.Controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/photoContent")
public class PhotoController {

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getcontent(@PathVariable String id) throws IOException {

        File mypic = new File("./data/Capture1.PNG");
        byte[] bytecontent= FileUtils.readFileToByteArray(mypic);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytecontent);//status ok thr request is picture and the body the byte so the google know it is picture not any thing
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) {
        try {
            // Save the uploaded file to the data directory with a modified filename
            String filename = id + "_photo";
            File tmpFile = new File("./data/" + filename);
            FileUtils.copyInputStreamToFile(file.getInputStream(), tmpFile);
            // file.transferTo();

            // Respond with a success message or the filename
            return ResponseEntity.ok("Image uploaded successfully. Filename: " + filename);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading image.");
        }
    }
}
