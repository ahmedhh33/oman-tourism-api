package com.omantourism.datamanager.Service;

import com.omantourism.datamanager.Model.photo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Service
public class PhotoServices<incommingphoto> {

    @Autowired
    PhotoInfoServices photoInfoServices;
    public ResponseEntity<byte[]> getcontent(@PathVariable String id) throws IOException {

        File mypic = new File("./data/Capture1.PNG");
        byte[] bytecontent= FileUtils.readFileToByteArray(mypic);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytecontent);//status ok thr request is picture and the body the byte so the google know it is picture not any thing
    }


    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) {
        try {
            // Save the uploaded file to the data directory with a modified filename
            //String filename = id + "_photo";
            File tmpFile = new File("%s/%s.%s".formatted("./data",id,"jpg"));

            FileUtils.copyInputStreamToFile(file.getInputStream(), tmpFile);
            // file.transferTo();
            photo newphoto = new photo("mycar",id,"mycar in mountans", tmpFile.getPath());

            photoInfoServices.photocolletion.add(newphoto);
            // Respond with a success message or the filename
            return ResponseEntity.ok("Image uploaded successfully"+newphoto);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading image.");
        }
    }

    public ResponseEntity<String> deleteImage(@PathVariable String id) {
        try {
            // to find file id  the photo
           // String filename = id + "_photo";
            //File fileToDelete = new File("./data/" + filename);
            File fileToDelete = new File("%s/%s.%s".formatted("./data",id,"jpg"));
            // Check if the file exists
            if (fileToDelete.exists()) {
                //FileUtils.delete()
                photoInfoServices.removephoto(id);
                if (fileToDelete.delete()) {
                    return ResponseEntity.ok("Image deleted successfully. Filename: " );
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting image.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found for ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting image.");
        }
    }
    public <phoroinformation> ResponseEntity<String> putImage(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        try {
            String filename = id + "_photo";
            File existingFile = new File("./data/" + filename);

            // Check if the file exists
            if (existingFile.exists()) {
                // Delete the file
                if (existingFile.delete()) {
                    // Save the uploaded file with the same filename
                    FileUtils.copyInputStreamToFile(file.getInputStream(), existingFile);

                    //photoInfoServices.updatephoto(id,);
                    // Respond with a success message or the updated filename
                    return ResponseEntity.ok("Image updated successfully. Filename: " + filename);
                } else {
                    return ResponseEntity.status(500).body("Error updating image.");
                }
            } else {
                return ResponseEntity.status(404).body("Image not found for ID: " + id);
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error updating image.");
        }
    }
}
