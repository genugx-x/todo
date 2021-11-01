package com.genug.todo.controller;

import com.genug.todo.model.dto.ResponseDto;
import com.genug.todo.model.dto.TodoDto;
import com.genug.todo.model.entity.TodoEntity;
import com.genug.todo.service.TodoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    public TodoService todoService;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo() {
        String str = todoService.testService();
        List<String> list = new ArrayList<>();
        list.add(str);
        return ResponseEntity.ok().body(ResponseDto.<String>builder().data(list).build());
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDto dto) {
        try {
            String temporaryUserId = "temporary-user"; // temporary user id.
            TodoEntity entity = TodoDto.toEntity(dto);
            entity.setId(null);
            entity.setUserId(temporaryUserId);
            List<TodoEntity> entities = todoService.create(entity);
            List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());
            ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodoList() {
        String temporaryUserId = "temporary-user";
        List<TodoEntity> entities = todoService.retrieve(temporaryUserId);
        List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());
        ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDto dto) {
        String temporaryUserId = "temporary-user";
        TodoEntity entity = TodoDto.toEntity(dto);
        entity.setUserId(temporaryUserId);
        List<TodoEntity> entities = todoService.update(entity);
        List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());
        ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDto dto) {
        try {
            String temporaryUserId = "temporary-user";
            TodoEntity entity = TodoDto.toEntity(dto);
            entity.setUserId(temporaryUserId);
            List<TodoEntity> entities = todoService.delete(entity);
            List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());
            ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }


}
