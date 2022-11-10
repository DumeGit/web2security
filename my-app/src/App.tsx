import React from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <button
          className="App-link"
          onClick={() => fetch("http://localhost:8080/user/sendCsrf", {
            method: "POST",body:JSON.stringify({"receiver":"picka@kurac.com","money":50}) ,credentials: "include", headers: {
                  "Content-Type": "application/json",
              }
          })}
        >
          Send secured
        </button><button
          className="App-link"
          onClick={() => fetch("http://localhost:8080/user/sendUnsecured", {
            method: "POST",body:JSON.stringify({"receiver":"picka@kurac.com","money":50}) ,credentials: "include", headers: {
                  "Content-Type": "application/json",
              }
          })}
        >
          Send unsecured
        </button>
      </header>
    </div>
  );
}

export default App;
