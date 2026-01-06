import React, { useEffect, useState } from 'react'
import { getAllTodos ,deleteTodo, updateComplete, updateInComplete } from '../services/TodoService';
import { useNavigate } from 'react-router-dom'
import { isAdminUser } from '../services/AuthService';

const ListTodoComponent = () => {

    // const dummyData = [
    //     {
    //         "id" : 1,
    //         "title" : "Learn Core Java",
    //         "description" : "Learn Core Java with examples",
    //         "completed" : false
    //     },
    //     {
    //         "id" : 2,
    //         "title" : "Learn Spring Core",
    //         "description" : "Learn Spring Core with examples",
    //         "completed" : false
    //     },
    //     {
    //         "id" : 3,
    //         "title" : "Learn Spring Boot",
    //         "description" : "Learn Spring Boot with examples",
    //         "completed" : false
    //     }
    // ]

    const [todos, setTodos] =  useState([]);

    const navigate = useNavigate();

    const isAdmin = isAdminUser();

    useEffect(() => {

        listTodos();

    },[] )

    function listTodos(){
        getAllTodos().then((response) => {
            setTodos(response.data);
        }).catch(error =>{
            console.error(error);
        })
    }

    function addNewTodo(){
        navigate('/add-todo');
    }

    function updateTodo(id){
        navigate(`/update-todo/${id}`);
    }

    function completeTodo(id){
        console.log(id);

        updateComplete(id).then((response) =>{
            listTodos();
        }).catch(error =>{
            console.error(error);
        })
    }

    function inCompleteTodo(id){

        console.log(id);
        updateInComplete(id).then((response) =>{
            listTodos();
        }).catch(error =>{
            console.error(error);
        })
    }

    function removeTodo(id){
        console.log(id);

        deleteTodo(id).then((response) =>{
            listTodos();
        }).catch(error =>{
            console.error(error);
        })
    }


  return (
    <div className='container'>
        <h2 className='text-center'>List of Todos</h2>
        {
            isAdmin && 
            <button className='btn btn-primary mb-2' onClick={addNewTodo}>Add Todo</button>
        }
        <div>
            <table className='table table-bordered table-striped'>
                <thead>
                    <tr>
                        <th>Todo Id</th>
                        <th>Todo Title</th>
                        <th>Todo Description</th>
                        <th>Todo Completed</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        todos.map(todo =>
                            <tr key={todo.id}>
                                <td>{todo.id}</td>
                                <td>{todo.title}</td>
                                <td>{todo.description}</td>
                                <td>{todo.completed ? 'YES' : 'NO'}</td>
                                <td>
                                {
                                    isAdmin && 
                                    <button type='button' className='btn btn-info' onClick={() => updateTodo(todo.id)}>Update</button>
                                }
                                {
                                    isAdmin && 
                                    <button type='button' className='btn btn-danger' onClick={() => removeTodo(todo.id)} 
                                        style={{marginLeft: '10px'}}
                                    >Delete</button>
                                }
                                <button type='button' className='btn btn-success' onClick={() => completeTodo(todo.id)} 
                                    style={{marginLeft: '10px'}}
                                >Complete</button>
                                <button type='button' className='btn btn-info' onClick={() => inCompleteTodo(todo.id)} 
                                    style={{marginLeft: '10px'}}
                                >Incomplete</button>
                            </td>
                            </tr>
                        )
                    }
                 
                </tbody>
            </table>
        </div>
    </div>
  )
}

export default ListTodoComponent;