package com.omao.wellness.image_upload.service;

import com.omao.wellness.image_upload.domain.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ImageService {
    Optional<Image> getImage(String id);
    Image saveImage(MultipartFile file) throws Exception;
}
