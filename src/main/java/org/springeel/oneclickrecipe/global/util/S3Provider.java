package org.springeel.oneclickrecipe.global.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class S3Provider {

    private final AmazonS3 amazonS3;
    private final String SEPARATOR = "/";
    @Value("${cloud.aws.s3.bucket.name}")
    public String bucket;
    private final String url = "https://onceclick.s3.ap-northeast-2.amazonaws.com/";


    private static ObjectMetadata setObjectMetadata(MultipartFile multipartFile) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());
        return metadata;
    }

    public String saveFile(MultipartFile multipartFile, String imageName) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }
        ObjectMetadata metadata = setObjectMetadata(multipartFile);
        amazonS3.putObject(bucket, imageName, multipartFile.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, imageName).toString();
    }

    public String originalFileName(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (fileType.equals(".png") || fileType.equals(".jpeg")) {
            fileName = UUID.randomUUID() + fileType;
            return fileName;
        } else {
            throw new IllegalArgumentException("잘못된 파일 형식입니다");
        }
    }

    public void createFolder(String folderName) {
        if (!amazonS3.doesObjectExist(bucket, folderName)) {
            amazonS3.putObject(
                bucket,
                folderName + SEPARATOR,
                new ByteArrayInputStream(new byte[0]),
                new ObjectMetadata());
        }
    }

    public void deleteImage(String imageName) {
        if (imageName == null) {
            return;
        }
        amazonS3.deleteObject(bucket, imageName);
    }

    public String updateImage(String imageName, String folderName, MultipartFile multipartFile)
        throws IOException {
        if (imageName == null) {
            String NewImage = originalFileName(multipartFile);
            imageName = url + folderName + SEPARATOR + NewImage;
            String saveImageUrl = folderName + SEPARATOR + NewImage;
            ObjectMetadata metadata = setObjectMetadata(multipartFile);
            amazonS3.putObject(bucket, saveImageUrl, multipartFile.getInputStream(), metadata);
        }
        imageName = imageName.replace(url, "");
        imageName = imageName.substring(imageName.lastIndexOf("/"));
        deleteImage(folderName + imageName);
        if (multipartFile.isEmpty()) {
            imageName = null;
        } else {
            String NewImage = originalFileName(multipartFile);
            imageName = url + folderName + SEPARATOR + NewImage;
            String saveImageUrl = folderName + SEPARATOR + NewImage;
            ObjectMetadata metadata = setObjectMetadata(multipartFile);
            amazonS3.putObject(bucket, saveImageUrl, multipartFile.getInputStream(), metadata);
        }
        return imageName;
    }
}
