package com.example.common;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;


@Data
@Builder
public class PageInfo<T> {
    private Long total;
    private List<T> list;

    public static <T> PageInfo<T> of(Page<T> page) {
        return PageInfo.<T>builder()
                .total(page.getTotalElements())
                .list(page.getContent())
                .build();
    }
}
