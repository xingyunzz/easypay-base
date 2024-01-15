package com.xingyun.easypaycommon.utils;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class S3Util {

    private static final String bucketName;

    static {
        Properties properties = LoadLocalConfig.properties;
        bucketName = properties.getProperty("s3.bucket.name");
    }

    public static String getPreSignUrl(String keyName){
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create("xy_upload");
        Region region = Region.AP_SOUTHEAST_1;
        S3Presigner presigner = S3Presigner.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                //.contentType("text/plain")
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .putObjectRequest(objectRequest)
                .build();

        PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);
        String myURL = presignedRequest.url().toString();
        presigner.close();
        return myURL;
    }


    public static void listBucketObjects(String bucketName) {

        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create("xy_upload");
        Region region = Region.AP_SOUTHEAST_1;
        S3Client s3 = S3Client.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();

        try {
            ListObjectsRequest listObjects = ListObjectsRequest
                    .builder()
                    .bucket(bucketName)
                    .build();

            ListObjectsResponse res = s3.listObjects(listObjects);
            List<S3Object> objects = res.contents();
            for (S3Object myValue : objects) {
                System.out.println(myValue.key());
                try {
                    GetObjectRequest objectRequest = GetObjectRequest
                            .builder()
                            .key(myValue.key())
                            .bucket(bucketName)
                            .build();

                    ResponseBytes<GetObjectResponse> objectBytes = s3.getObjectAsBytes(objectRequest);
                    byte[] data = objectBytes.asByteArray();

                    // Write the data to a local file.
                    File myFile = new File("~/Desktop/verify/h5/"+myValue.key() );
                    if (!myFile.getParentFile().exists()){
                        myFile.getParentFile().mkdirs();
                    }
                    OutputStream os = new FileOutputStream(myFile);
                    os.write(data);
                    os.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (S3Exception e) {
                    System.err.println(e.awsErrorDetails().errorMessage());
                    System.exit(1);
                }
            }

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    //convert bytes to kbs.
    private static long calKb(Long val) {
        return val/1024;
    }

    public static void main(String[] args) {
        listBucketObjects("lottery-front");
    }





}
