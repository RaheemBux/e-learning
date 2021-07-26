package com.test.studentv.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationUtil {

    private Integer currentPage;
    private Integer itemsPerPages;
    private String sortBy;
    private String direction;
}
