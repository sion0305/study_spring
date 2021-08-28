package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor //모든 argument 포함한 생성자 만들기
public class SearchParam {

    private String account;
    private String email;
    private int page;

}
