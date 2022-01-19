package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberPageDto<T> {

    private Integer totalPages;
    private Long totalElements;
    private String nextPage;
    private String previousPage;
    private List<T> list;

}
