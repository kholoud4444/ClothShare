package com.ntg.backend.dto.ResponsePagination;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
@Data
public class PageDto <T>{
    private List<T> content;
    private long totalElements;
    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private Boolean isLast;

    // Constructor that accepts multiple arguments for pagination details
    public PageDto(List<T> content, long totalElements, int totalPages, int pageNumber, int pageSize, Boolean isLast) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.isLast = isLast;
    }
}


