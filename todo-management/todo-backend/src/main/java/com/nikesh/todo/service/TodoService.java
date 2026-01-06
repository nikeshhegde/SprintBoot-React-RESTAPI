package com.nikesh.todo.service;

import com.nikesh.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getTodos();

    TodoDto updateTodo(Long id, TodoDto todoDto);

    String deleteTodo(Long id);

    TodoDto completeTodo(Long id);

    TodoDto inCompleteTodo(Long id);
}
