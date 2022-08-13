package com.InfluencerMarketpalce.serverside.controller;

import java.io.IOException;

import lombok.var;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.InfluencerMarketpalce.serverside.utility.FileDownloadUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FileDownloadController {
    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

//    @GetMapping("/showFile/{fileName:.+}")
//    public ResponseEntity<?> downloadFileFilename(@PathVariable("fileName") String fileName) {
//        // Load file as Resource
//        FileDownloadUtil downloadUtil = new FileDownloadUtil();
//
//        Resource resource = null;
//        try {
//            resource = downloadUtil.getFileAsResource(fileName);
//        } catch (IOException e) {
//            return ResponseEntity.internalServerError().build();
//        }
//
//        if (resource == null) {
//            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
//        }
//
//        String contentType = "application/octet-stream";
//        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }

    @RequestMapping(value = "/sid", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImage() throws IOException {

        var imgFile = new ClassPathResource("Files-Upload/Lyq6lwGB-PasfotoBerwarna.jpeg");

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
}
