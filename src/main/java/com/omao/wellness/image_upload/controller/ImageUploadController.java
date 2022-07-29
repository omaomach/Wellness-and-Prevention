package com.omao.wellness.image_upload.controller;

import com.omao.wellness.image_upload.domain.Image;
import com.omao.wellness.image_upload.domain.ResponseData;
import com.omao.wellness.image_upload.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api/v1/image")
@RequiredArgsConstructor
public class ImageUploadController {
    private final ImageUploadService imageUploadService;
    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadImage(@RequestParam(value = "image")MultipartFile file) throws Exception {
        Image image = imageUploadService.saveImage(file);
        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(image.getId())
                .toUriString();
        return ResponseEntity.status(CREATED).body(new ResponseData(image.getFileName(),downloadUrl,image.getFileType(),file.getSize()));
    }
    @GetMapping(value = "/download/{file_id}")
    public ResponseEntity<?> downloadImage(@PathVariable(value = "file_id")String id){
        Optional<Image> image = imageUploadService.getImage(id);
        return ResponseEntity.status(OK).contentType(MediaType.parseMediaType(image.get().getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""+image.get().getFileName()+"\"")
                .body(new ByteArrayResource(image.get().getData()));
    }
}
