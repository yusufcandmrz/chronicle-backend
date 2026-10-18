package com.yusufcandmrz.chronicle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadBookResponse {
    private String title;
    private Integer releaseYear;
    private List<String> authorList;
    private List<String> translatorList;
    private String publisher;
}
