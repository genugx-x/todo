package com.genug.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("todo")
public class TodoController {

    @GetMapping
    public ResponseEntity<?> testTodo() {
        return ResponseEntity.ok().body("Hello Todo World!");
    }
}
