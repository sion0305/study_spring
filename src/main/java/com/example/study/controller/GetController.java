package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //localhost:9090/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path="/getMethod")
    // localhost:9090/api/getMethod
    public String getRequest(){

        return "Hi getMethod";
    }

    @GetMapping("/getParameter")// localhost:9090/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name="password") String pwd){
        String password = "bbb";

        System.out.println("id : " + id);
        System.out.println("pw : " + pwd);

        return id+pwd;
    }

    // localhost:9090/api/getMultiParameter?account=abcd&email=study@gmail.com&page=10
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // {"account" : "", "email" : "", "page" : 0}
        // 객체를 리턴하면 자동으로 json으로 변환됨
        return searchParam;
    }
}
