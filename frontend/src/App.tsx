import React, {useEffect, useState} from 'react';
import './App.css';
import {LoginForm} from "./page/LoginForm";
import HomePage from "./page/HomePage";
import {getCsrf, getCurrentUser} from "./api/auth";

function App() {

    const [user, setUser] = useState();

    useEffect(() => {
        setCsrf()
        getCurrentUser().then(item => {
            setUser(item)
        });
    }, [])

    async function setCsrf() {
        let response = await getCsrf()
        localStorage.setItem("csrfToken", response.token)
    }

  return (
      <div>
          { user ? <HomePage user={user}/> : <LoginForm setUser={setUser}/>}
      </div>
  );
}

export default App;
