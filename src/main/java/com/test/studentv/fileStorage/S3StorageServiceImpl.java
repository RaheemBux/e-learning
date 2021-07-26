package com.test.studentv.fileStorage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3StorageServiceImpl implements StorageService{
	
	@Autowired
	AWSUtil awsUtil;

	@Override
	public String store(MultipartFile file) {
		 String filename = StringUtils.cleanPath(file.getOriginalFilename());
	        String extension = StringUtils.getFilenameExtension(filename);
	        String justFilename = filename.replace("."+extension, "");
	        String storedFilename = System.currentTimeMillis() + "_" + justFilename + "." + extension;
	        try {
	            if (file.isEmpty()) {
	                throw new StorageException("Failed to store empty file " + filename);
	            }
	            if (filename.contains("..")) {
	                // This is a security check
	                throw new StorageException(
	                        "Cannot store file with relative path outside current directory "
	                                + filename);
	            }
	            try (InputStream inputStream = file.getInputStream()) {
	                awsUtil.uploadFileToS3bucket(storedFilename, file.getInputStream());
	                return storedFilename;
	            }
	        }
	        catch (IOException e) {
	            throw new StorageException("Failed to store file " + filename, e);
	        }
	}

	@Override
	public void delete(String filename) {
		awsUtil.deleteFileFromS3bucket(filename);
	}
	
	@Override
	public InputStream downloadFile(String filename) {
		return new ByteArrayInputStream(awsUtil.download(filename));
	}

}

