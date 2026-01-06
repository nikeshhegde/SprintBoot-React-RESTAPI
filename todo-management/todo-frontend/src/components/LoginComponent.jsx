import React, { useState } from 'react';
import { loginAPICall, saveLoggedInUser, storeToken } from '../services/AuthService';
import { useNavigate } from 'react-router-dom';

const LoginComponent = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();

    async function loginUser(e){
        e.preventDefault();

        const login = { username,  password};
        console.log(login);

        await loginAPICall(username, password).then((response) => {
            console.log(response.data);

            //const token = 'Basic ' + window.btoa(username + ":" + password); Basic Auth

            const token = 'Bearer ' + response.data.accessToken;
            const userRole = response.data.role;

            storeToken(token);

            saveLoggedInUser(username, userRole);

            navigate('/todos');

            window.location.reload(false);

        }).catch(error => {
            console.error(error);
        })
    }

  return (
    <div className='container'>
        <br/>
        <br/>
        <div className='row'>
            <div className='col-md-6 offset-md-3'>
                <div className='card'>
                    <div className='card-header'>
                        <h2 className='text-center'> Login</h2>
                    </div>
                    <div className='card-body'>
                        <form>
                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'>Username or Email</label>
                                <div className='col-md-9'>
                                    <input
                                        type='text'
                                        name='username'
                                        className='form-control'
                                        placeholder='Enter Username'
                                        value={username}
                                        onChange={(e) => setUsername(e.target.value)}
                                    >
                                    </input>
                                </div>
                            </div>

                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'>Password</label>
                                <div className='col-md-9'>
                                    <input
                                        type='password'
                                        name='password'
                                        className='form-control'
                                        placeholder='Enter Password'
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                    >
                                    </input>
                                </div>
                            </div>

                            <div className='form-group mb-3'>

                                <button className='btn btn-primary' onClick={loginUser}>Login</button>
                                <a href='/register' style={{marginLeft: '10px'}}>Not Registered ! Register here</a>

                            </div>
                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
  )
}

export default LoginComponent;