package com.omao.wellness.image_upload.service;

import com.omao.wellness.image_upload.domain.Image;
import com.omao.wellness.image_upload.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageUploadService implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public Image saveImage(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains(".."))
            throw new Exception("Filename has invalid characters");
        Image image = new Image(fileName, file.getContentType(), file.getBytes());
        return imageRepository.save(image);
    }

    @Override
    public Optional<Image> getImage(String id) {
        return imageRepository.findById(id);
    }

}
