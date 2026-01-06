package com.nikesh.todo.controller;

import com.nikesh.todo.dto.TodoDto;
import com.nikesh.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto){

        TodoDto createdTodoDto = todoService.addTodo(todoDto);
        return new ResponseEntity<>(createdTodoDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") Long todoId){

        TodoDto todoDto = todoService.getTodo(todoId);
        return ResponseEntity.ok(todoDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<TodoDto>> getTodos(){

        List<TodoDto> todoList = todoService.getTodos();
        return ResponseEntity.ok(todoList);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long todoId, @RequestBody TodoDto todoDto){

        TodoDto updatedTodo = todoService.updateTodo(todoId, todoDto);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){

        String message = todoService.deleteTodo(todoId);
        return ResponseEntity.ok(message);
    }

    @PatchMapping("{id}/complete")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TodoDto> updateCompleted(@PathVariable("id") Long todoId){
        TodoDto todoDto = todoService.completeTodo(todoId);
        return ResponseEntity.ok(todoDto);
    }

    @PatchMapping("{id}/incomplete")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TodoDto> updateInComplete(@PathVariable("id") Long todoId){
        TodoDto todoDto = todoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(todoDto);
    }

}
