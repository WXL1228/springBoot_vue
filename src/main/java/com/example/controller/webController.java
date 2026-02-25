package com.example.controller;


import com.example.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "通用模块")
public class webController {

    @GetMapping(value ="/success")
    public Result success () {
        return Result.success();
    }


    @Operation(summary = "错误",description = "错误方法")
    @GetMapping(value ="/error1")
    public Result error1 () {
        return Result.error("error");
    }
}
