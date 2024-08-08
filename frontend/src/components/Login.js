import React, { useState } from 'react';
import './Login.css';

function Login({ setAuthenticated }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async () => {
    const response = await fetch('http://localhost:6767/api/techcareer/todo/find/all', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(username + ':' + password)
      }
    });

    if (response.ok) {
      setAuthenticated(true);
      localStorage.setItem('auth', 'Basic ' + btoa(username + ':' + password));
    } else {
      alert('Login failed');
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <div className="login-form">
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button onClick={handleLogin}>Login</button>
      </div>
    </div>
  );
}

export default Login;
