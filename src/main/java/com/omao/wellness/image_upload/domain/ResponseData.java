package com.omao.wellness.image_upload.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private String fileName;
    private String imageUrl;
    private String fileType;
    private Long fileSize;
}
