package com.test.studentv.fileStorage;


import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Service
public class AWSUtil {
	// AmazonS3 Client, in this object you have all AWS API calls about S3.
    private AmazonS3 amazonS3;

    // Your bucket URL, this URL is https://{bucket-name}.s3-{region}.amazonaws.com/
    @Value("${amazon.s3.endpoint}")
    private String url;

    // Your bucket name.
    @Value("${amazon.s3.bucket-name}")
    private String bucketName;

    // The IAM access key.
    @Value("${amazon.s3.access-key}")
    private String accessKey;

    // The IAM secret key.
    @Value("${amazon.s3.secret-key}")
    private String secretKey;

    protected AmazonS3 getClient() {
        return amazonS3;
    }

    protected String getUrl() {
        return url;
    }

    protected String getBucketName() {
        return bucketName;
    }
    
    @PostConstruct
    private void init() {

        // Init your AmazonS3 credentials using BasicAWSCredentials.
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        // Start the client using amazonS3Builder, here we goes to make a standard cliente, in the
        // region SA_EAST_1, and the basic credentials.
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.EU_WEST_3)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
    
    
    public void uploadFileToS3bucket(String fileName, InputStream fileInputStream) {
        amazonS3.putObject(bucketName,fileName, fileInputStream, null);
    }

    public S3Object downloadFileFromS3bucket(String fileName) {
        return amazonS3.getObject(bucketName,  fileName);
    }

    public void deleteFileFromS3bucket(String fileName) {
        amazonS3.deleteObject(bucketName, fileName);
    }
  
    public byte[] download(String fileName) {
        try {
            S3Object object = amazonS3.getObject(bucketName, fileName);
            S3ObjectInputStream objectContent = object.getObjectContent();
            return IOUtils.toByteArray(objectContent);
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download the file", e);
        }
    }

}
