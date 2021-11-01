package com.genug.todo.service;

import com.genug.todo.model.entity.TodoEntity;
import com.genug.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public String testService() {
        TodoEntity entity = TodoEntity.builder().title("My first todo item!!!").build();
        todoRepository.save(entity);
        TodoEntity savedEntity = todoRepository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }
}
