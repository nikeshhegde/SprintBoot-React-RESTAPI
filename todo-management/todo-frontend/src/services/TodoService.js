import axios from 'axios';
import { getToken } from './AuthService';

const TODO_REST_API_URL = "http://localhost:8080/api/todos";

// Add a request interceptor
axios.interceptors.request.use(function (config) {
    
    config.headers['Authorization'] = getToken();

    return config;
    
  }, function (error) {
    // Do something with request error
    return Promise.reject(error);
  }
);


export const getAllTodos = () => axios.get(TODO_REST_API_URL);

export const addTodo = (todo) => axios.post(TODO_REST_API_URL, todo);

export const getTodoById = (todoId) => axios.get(TODO_REST_API_URL + '/' + todoId);

export const updateTodo = (todoId, todo) => axios.put(TODO_REST_API_URL + '/' + todoId, todo);

export const deleteTodo = (todoId) => axios.delete(TODO_REST_API_URL + '/' + todoId);

export const updateComplete = (todoId, complete) => axios.patch(TODO_REST_API_URL + '/' + todoId + '/complete', complete);

export const updateInComplete = (todoId, complete) => axios.patch(TODO_REST_API_URL + '/' + todoId+ '/incomplete', complete);