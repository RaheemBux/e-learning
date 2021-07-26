package com.test.studentv.fileStorage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileResponse {

    // The FileResponse class is used to return a JSON response for RESTful web services.

    private String name;
    private String uri;
    private String type;
    private long size;

    public FileResponse(String name, String uri, String type, long size) {
        this.name = name;
        this.uri = uri;
        this.type = type;
        this.size = size;
    }
}
