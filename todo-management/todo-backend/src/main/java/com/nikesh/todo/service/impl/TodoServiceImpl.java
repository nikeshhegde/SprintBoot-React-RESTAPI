package com.nikesh.todo.service.impl;

import com.nikesh.todo.dto.TodoDto;
import com.nikesh.todo.entity.Todo;
import com.nikesh.todo.exception.ResourceNotFoundException;
import com.nikesh.todo.repository.TodoRepository;
import com.nikesh.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(final TodoDto todoDto) {


        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public TodoDto getTodo(final Long id) {

        Todo todo = todoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));

        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getTodos() {

        List<Todo> todoList = todoRepository.findAll();

        return todoList.stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(final Long id, final TodoDto todoDto) {

        Todo todo = todoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        todoRepository.save(todo);

        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public String deleteTodo(final Long id) {

        Todo todo = todoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));

        todoRepository.deleteById(id);

        return "Todo deleted successfully with id: "+ id;
    }

    @Override
    public TodoDto completeTodo(final Long id) {

        Todo todo = todoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));

        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {

        Todo todo = todoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));

        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo,TodoDto.class);
    }
}
