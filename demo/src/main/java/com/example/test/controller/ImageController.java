package com.example.test.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    private Map<String, byte[]> imageDatabase = new HashMap<>();

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        imageDatabase.put(file.getOriginalFilename(), file.getBytes());
        return ResponseEntity.ok("Image uploaded successfully: " + file.getOriginalFilename());
    }

    @GetMapping("/view/{imageName}")
    public ResponseEntity<byte[]> viewImage(@PathVariable String imageName) {
        byte[] image = imageDatabase.get(imageName);
        if (image == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageName + "\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }
}
