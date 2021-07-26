package com.test.studentv.fileStorage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "storage")
@Getter
@Setter
public class StorageProperties {
    private String location;

    /* Notice the prefix= "storage" attribute in the above annotation.
     * It instructs @ConfigurationProperties to bind all the properties
     * that start with storage prefix to their corresponding attributes
     * of POJO class when the application is started.
     */

    /* The next step is to enable the ConfigurationProperties feature by
     * adding @EnableConfigurationProperties annotation to our main configuration class.
     */
}
