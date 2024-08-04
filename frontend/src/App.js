import React, { useState } from 'react';
import TodoInput from './components/TodoInput';
import TodoList from './components/TodoList';
import './App.css';

function App() {
  const [todos, setTodos] = useState([]);
  const [filter, setFilter] = useState('all');

  const fetchAllTodos = () => {
    fetch('http://localhost:6767/api/techcareer/todo/find/all')
      .then(response => response.json())
      .then(data => {
        console.log('Fetched todos:', data); // Verileri console'a yazdırma
        setTodos(data);
      })
      .catch(error => console.error('Error fetching data:', error));
  };

  const addTodo = (task) => {
    const newTodo = { name: task, completed: false };
    fetch('http://localhost:6767/api/techcareer/todo/add', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newTodo)
    })
    .then(response => response.json())
    .then(data => setTodos([...todos, data]))
    .catch(error => console.error('Error adding todo:', error));
  };

  const toggleComplete = (id) => {
    const updatedTodos = todos.map(todo =>
      todo.id === id ? { ...todo, completed: !todo.completed } : todo
    );
    setTodos(updatedTodos);
    
    const updatedTodo = updatedTodos.find(todo => todo.id === id);
    fetch(`http://localhost:6767/api/techcareer/todo/update`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedTodo)
    })
    .catch(error => console.error('Error updating todo:', error));
  };

  const deleteTodo = (id) => {
    console.log(`Deleting todo with id: ${id}`); // Silinen ID'yi yazdırma
    fetch(`http://localhost:6767/api/techcareer/todo/delete/${id}`, {
      method: 'DELETE'
    })
    .then(response => {
      if (response.ok) {
        setTodos(todos.filter(todo => todo.id !== id));
        console.log('Todo deleted successfully');
      } else {
        console.error('Failed to delete todo');
      }
    })
    .catch(error => console.error('Error deleting todo:', error));
  };

  const editTodo = (id, newTask) => {
    const updatedTodos = todos.map(todo =>
      todo.id === id ? { ...todo, name: newTask } : todo
    );
    setTodos(updatedTodos);

    const updatedTodo = updatedTodos.find(todo => todo.id === id);
    fetch(`http://localhost:6767/api/techcareer/todo/update`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedTodo)
    })
    .catch(error => console.error('Error updating todo:', error));
  };

  const deleteDoneTasks = () => {
    const doneTasks = todos.filter(todo => todo.completed);

    doneTasks.forEach(task => {
      fetch(`http://localhost:6767/api/techcareer/todo/delete/${task.id}`, {
        method: 'DELETE'
      })
      .catch(error => console.error('Error deleting done todo:', error));
    });

    setTodos(todos.filter(todo => !todo.completed));
  };

  const deleteAllTasks = () => {
    fetch('http://localhost:6767/api/techcareer/todo', {
      method: 'DELETE'
    })
    .catch(error => console.error('Error deleting all todos:', error));

    setTodos([]);
  };

  const filteredTodos = todos.filter(todo => {
    if (filter === 'all') return true;
    if (filter === 'done') return todo.completed;
    if (filter === 'todo') return !todo.completed;
    return true;
  });

  return (
    <div className="App">
      <TodoInput addTodo={addTodo} />
      <div className="filter-buttons">
        <button onClick={() => { fetchAllTodos(); setFilter('all'); }}>All</button>
        <button onClick={() => setFilter('done')}>Done</button>
        <button onClick={() => setFilter('todo')}>Todo</button>
      </div>
      <div className="separator"></div>
      <TodoList
        todos={filteredTodos}
        toggleComplete={toggleComplete}
        deleteTodo={deleteTodo}
        editTodo={editTodo}
      />
      <div className="clear-buttons">
        <button onClick={deleteDoneTasks}>Delete done tasks</button>
        <button onClick={deleteAllTasks}>Delete all tasks</button>
      </div>
    </div>
  );
}

export default App;
