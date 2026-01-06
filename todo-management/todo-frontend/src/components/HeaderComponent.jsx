import React from 'react'
import { NavLink } from 'react-router-dom';
import { isLoggedIn, logoutUser } from '../services/AuthService';
import { useNavigate } from 'react-router-dom';

const HeaderComponent = () => {

  const isAuth = isLoggedIn();


  const navigate = useNavigate();

   function handleLogout(){
    logoutUser();
    navigate('/login');

  }

  return (
    <div>
        <header>
            <nav className='navbar navbar-expand-lg navbar-dark bg-dark'>
                <a href='http://localhost:3000' className="navbar-brand"> Todo Management Application </a>

                <div className="collapse navbar-collapse" id="navbarText">
                  <ul className="navbar-nav mr-auto">
                    {
                      isAuth &&
                      <li className="nav-item">
                        <NavLink className='nav-link' to='/todos'>Todos</NavLink>
                    </li>
                    }
                    
                  </ul>
                </div>
                <ul className="navbar-nav mr-auto">
                  {
                    !isAuth &&
                    <li className="nav-item">
                      <NavLink className='nav-link' to='/register'>Register</NavLink>
                    </li>
                  }
                  {
                    !isAuth &&
                    <li className="nav-item">
                      <NavLink className='nav-link' to='/login'>Login</NavLink>
                    </li>
                  }

                  {
                    isAuth &&
                    <li className="nav-item">
                      <NavLink className='nav-link' to='/login' onClick={handleLogout}>Logout</NavLink>
                    </li>
                  }
                  </ul>
            </nav>
        </header>
    </div>
  )
}

export default HeaderComponent;