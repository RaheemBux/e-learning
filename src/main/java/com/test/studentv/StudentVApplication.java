package com.test.studentv;

import com.test.studentv.fileStorage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class StudentVApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentVApplication.class, args);
        System.out.println("Project has been Started");
    }
}
