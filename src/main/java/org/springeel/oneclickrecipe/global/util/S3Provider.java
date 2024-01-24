package org.springeel.oneclickrecipe.global.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class S3Provider {

    private final AmazonS3 amazonS3;
    public final String SEPARATOR = "/";
    @Value("${cloud.aws.s3.bucket.name}")
    public String bucket;
    public final String url = "https://onceclick.s3.ap-northeast-2.amazonaws.com/";


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
        if (Objects.equals(multipartFile.getContentType(), "image/png")
            || Objects.equals(multipartFile.getContentType(), "image/jpeg")) {
            String fileType = switch (multipartFile.getContentType()) {
                case "image/png" -> ".png";
                case "image/jpeg" -> ".jpg";
                default -> throw new IllegalStateException(
                    "Unexpected value: " + multipartFile.getContentType());
            };
            return UUID.randomUUID() + fileType;
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

    public void delete(String imageName) {
        if (imageName == null) {
            return;
        }
        amazonS3.deleteObject(bucket, imageName);
    }

    public String updateImage(String imageName, String folderName, MultipartFile multipartFile)
        throws IOException {
        // S3에 대한 데이터 저장이나 변경이 없을 경우
        if (imageName == null && multipartFile.isEmpty()) {
            return null;
        } else {
            // S3에 대한 정보가 DB에 저장되었지만 해당 내용을 삭제하고 싶을 때
            if (multipartFile.isEmpty()) {
                imageName = imageName.replace(url, "");
                imageName = imageName.substring(imageName.lastIndexOf("/"));
                delete(folderName + imageName);
                return null;
            } else if (imageName == null) {
                // S3에 대한 정보 저장이 없다가 추가하고 싶을때
                imageName = originalFileName(multipartFile);
                imageName = url + folderName + SEPARATOR + imageName;
                String saveImageUrl = folderName + SEPARATOR + imageName;
                ObjectMetadata metadata = setObjectMetadata(multipartFile);
                amazonS3.putObject(bucket, saveImageUrl, multipartFile.getInputStream(), metadata);
            } else {
                // S3에 대한 정보 교체
                imageName = imageName.replace(url, "");
                imageName = imageName.substring(imageName.lastIndexOf("/"));
                delete(folderName + imageName);// 이미지 내용을 변경하고 싶거나 또는 유지하고 싶을 때
                String NewImage = originalFileName(multipartFile);
                imageName = url + folderName + SEPARATOR + NewImage;
                String saveImageUrl = folderName + SEPARATOR + NewImage;
                ObjectMetadata metadata = setObjectMetadata(multipartFile);
                amazonS3.putObject(bucket, saveImageUrl, multipartFile.getInputStream(), metadata);
            }
        }
        return imageName;
    }
}
