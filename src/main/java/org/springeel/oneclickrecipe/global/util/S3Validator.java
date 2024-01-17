package org.springeel.oneclickrecipe.global.util;

import com.amazonaws.services.s3.AmazonS3;

public class S3Validator {

    public static void validate(AmazonS3 amazonS3, String bucket, String filename) {
        if (isNullFilename(filename) || !isExistFile(amazonS3, bucket, filename)) {
            throw new RuntimeException("NOT_FOUNMD");
        }
    }

    private static boolean isNullFilename(String filename) {
        return filename == null;
    }

    private static boolean isExistFile(AmazonS3 amazonS3, String bucket, String filename) {
        return amazonS3.doesObjectExist(bucket, filename);
    }
}
