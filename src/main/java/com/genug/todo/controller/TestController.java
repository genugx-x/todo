package com.genug.todo.controller;

import com.genug.todo.model.dto.ResponseDto;
import com.genug.todo.model.dto.TestRequestBodyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test") // 리소스
public class TestController {

    @GetMapping
    public String testController() {
        return "Hello World!";
    }

    @GetMapping("/testGetMapping")
    public String testControllerWithPath() {
        return "Hello World! testGetMapping";
    }

    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
        return "Hello World ID " + id;
    }

    @GetMapping("/testRequestParam")
    public String testControllerWithRequestParam(@RequestParam(required = false) int id) {
        return "Hello World ID " + id;
    }

    @GetMapping("/testRequestBody")
    public String testControllerWithRequestBody(@RequestBody TestRequestBodyDto testRequestBodyDto) {
        return "Hello World! ID " + testRequestBodyDto.getId() + " Message : " + testRequestBodyDto.getMessage();
    }

    @GetMapping("/testResponseBody")
    public ResponseDto<String> testControllerResponseBody() {
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm ResponseDto");
        return ResponseDto.<String>builder()
                .data(list)
                .build();
    }

    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity() {
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm ResponseEntity. And you got 400!");
        return ResponseEntity.badRequest().body(ResponseDto.<String>builder().data(list).build());
    }


}
