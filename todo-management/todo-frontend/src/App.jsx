import './App.css'
import ListTodoComponent from "./components/ListTodoComponent";
import HeaderComponent from "./components/HeaderComponent";
import FooterComponent from "./components/FooterComponent";
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import TodoComponent from './components/TodoComponent';
import RegisterComponent from './components/RegisterComponent';
import LoginComponent from './components/LoginComponent';
import { isLoggedIn } from './services/AuthService';

function App() {

  function AuthenticatedRoute({children}){

    const isAuth = isLoggedIn();

    if(isAuth){
      return children;
    }
    return <Navigate to="/" />

  }

  return (
    <>
      <BrowserRouter>
        <HeaderComponent/>
        <Routes>
          {/* // http://localhost:3000 */}
          <Route path='/' element={ <LoginComponent />}> </Route>
          {/* // http://localhost:3000/todos */}
          <Route path='/todos' element={ 
            <AuthenticatedRoute>
              <ListTodoComponent />
            </AuthenticatedRoute>
          }></Route>
          {/* // http://localhost:3000/add-todo */}
          <Route path='/add-todo' element={ 
            <AuthenticatedRoute>
              <TodoComponent />
            </AuthenticatedRoute>
           }> </Route>
          {/* // http://localhost:3000/update-todo/1 */}
          <Route path='/update-todo/:id' element={ 
            <AuthenticatedRoute>
              <TodoComponent />
            </AuthenticatedRoute>}> </Route>
          {/* // http://localhost:3000/delete-todo/1 */}
          <Route path='/delete-todo/:id' element={
            <AuthenticatedRoute>
              <TodoComponent />
            </AuthenticatedRoute>}> </Route>
          {/* // http://localhost:3000/complete-todo/1 */}
          <Route path='/complete-todo/:id' element={
            <AuthenticatedRoute>
              <TodoComponent />
            </AuthenticatedRoute> }> </Route>
          {/* // http://localhost:3000/incomplete-todo/1 */}
          <Route path='/incomplete-todo/:id' element={ 
             <AuthenticatedRoute>
                <TodoComponent />
             </AuthenticatedRoute>}> </Route>
          {/* // http://localhost:3000/register */}
          <Route path='/register' element={ <RegisterComponent />}> </Route>
          {/* // http://localhost:3000/login */}
          <Route path='/login' element={ <LoginComponent />}> </Route>
        </Routes>
        <FooterComponent/>
      </BrowserRouter>
    </>
  )
}

export default App;
