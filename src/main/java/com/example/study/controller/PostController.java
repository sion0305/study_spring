package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //class의 requestmapping 주소가 겹치는 것은 상관 없음
public class PostController {

    // html <form>
    // ajax 검색
    // http post body 에 데이터를 넣어서 보내겟다
    // json, xml, multipart-form / text-plain

    @PostMapping("/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }

    @PutMapping
    public void put(){

    }

    @PatchMapping
    public void patch(){

    }
}
