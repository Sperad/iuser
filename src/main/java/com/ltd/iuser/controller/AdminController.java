package com.ltd.iuser.controller;

import com.ltd.iuser.enums.Code;
import com.ltd.iuser.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/search")
    public Result search() {
        System.out.println("dadasd");
        return new Result(Code.SUCCESS, "");
    }
}
