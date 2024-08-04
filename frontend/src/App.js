import React, { useState, useEffect } from 'react';
import TodoInput from './components/TodoInput';
import TodoList from './components/TodoList';
import './App.css';

function App() {
  const [todos, setTodos] = useState([]);
  const [filter, setFilter] = useState('all');
  const [currentPage, setCurrentPage] = useState(1);
  const todosPerPage = 8; // Her sayfada 8 todo göstermek için

  useEffect(() => {
    fetchAllTodos();
  }, []);

  const sortTodosById = (todos) => {
    return todos.sort((a, b) => a.id - b.id);
  };

  const fetchAllTodos = () => {
    fetch('http://localhost:6767/api/techcareer/todo/find/all')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('All Todos:', data);
        if (Array.isArray(data)) {
          setTodos(sortTodosById(data));
          setCurrentPage(1); // Sayfa numarasını 1 olarak ayarla
        } else {
          console.error('Received data is not an array:', data);
        }
      })
      .catch(error => console.error('Error fetching data:', error));
  };

  const fetchCompletedTodos = () => {
    fetch('http://localhost:6767/api/techcareer/todo/find/completed')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('Completed Todos:', data);
        if (Array.isArray(data)) {
          setTodos(sortTodosById(data));
          setCurrentPage(1); // Sayfa numarasını 1 olarak ayarla
        } else {
          console.error('Received data is not an array:', data);
        }
      })
      .catch(error => console.error('Error fetching completed todos:', error));
  };

  const fetchNotCompletedTodos = () => {
    fetch('http://localhost:6767/api/techcareer/todo/find/notcompleted')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('Not Completed Todos:', data);
        if (Array.isArray(data)) {
          setTodos(sortTodosById(data));
          setCurrentPage(1); // Sayfa numarasını 1 olarak ayarla
        } else {
          console.error('Received data is not an array:', data);
        }
      })
      .catch(error => console.error('Error fetching not completed todos:', error));
  };

  const addTodo = (task) => {
    const newTodo = { name: task, completed: false };
    fetch('http://localhost:6767/api/techcareer/todo/add', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newTodo)
    })
    .then(response => response.json())
    .then(data => setTodos(sortTodosById([...todos, data])))
    .catch(error => console.error('Error adding todo:', error));
  };

  const toggleComplete = (id) => {
    const updatedTodos = todos.map(todo =>
      todo.id === id ? { ...todo, completed: !todo.completed } : todo
    );
    setTodos(sortTodosById(updatedTodos));

    const updatedTodo = updatedTodos.find(todo => todo.id === id);
    fetch(`http://localhost:6767/api/techcareer/todo/update`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedTodo)
    })
    .catch(error => console.error('Error updating todo:', error));
  };

  const deleteTodo = (id) => {
    fetch(`http://localhost:6767/api/techcareer/todo/delete/${id}`, {
      method: 'DELETE'
    })
    .then(response => {
      if (response.ok) {
        setTodos(sortTodosById(todos.filter(todo => todo.id !== id)));
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
    setTodos(sortTodosById(updatedTodos));

    const updatedTodo = updatedTodos.find(todo => todo.id === id);
    fetch(`http://localhost:6767/api/techcareer/todo/update`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedTodo)
    })
    .catch(error => console.error('Error updating todo:', error));
  };

  const deleteDoneTasks = () => {
    fetch('http://localhost:6767/api/techcareer/todo/deleteCompletedAll', {
      method: 'DELETE'
    })
    .then(response => {
      if (response.ok) {
        setTodos(todos.filter(todo => !todo.completed));
      } else {
        console.error('Failed to delete completed todos');
      }
    })
    .catch(error => console.error('Error deleting completed todos:', error));
  };

  const deleteAllTasks = () => {
    fetch('http://localhost:6767/api/techcareer/todo/deleteAll', {
      method: 'DELETE'
    })
    .then(response => {
      if (response.ok) {
        setTodos([]);
      } else {
        console.error('Failed to delete all todos');
      }
    })
    .catch(error => console.error('Error deleting all todos:', error));
  };

  const filteredTodos = todos.filter(todo => {
    if (filter === 'all') return true;
    if (filter === 'done') return todo.completed;
    if (filter === 'todo') return !todo.completed;
    return true;
  });

  const indexOfLastTodo = currentPage * todosPerPage;
  const indexOfFirstTodo = indexOfLastTodo - todosPerPage;
  const currentTodos = filteredTodos.slice(indexOfFirstTodo, indexOfLastTodo);

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const nextPage = () => setCurrentPage(prevPage => Math.min(prevPage + 1, Math.ceil(filteredTodos.length / todosPerPage)));
  const prevPage = () => setCurrentPage(prevPage => Math.max(prevPage - 1, 1));

  return (
    <div className="App">
      <TodoInput addTodo={addTodo} />
      <div className="filter-buttons">
        <button onClick={fetchAllTodos}>All</button>
        <button onClick={fetchCompletedTodos}>Done</button>
        <button onClick={fetchNotCompletedTodos}>Todo</button>
      </div>
      <div className="pagination-container">
        <div className="pagination">
          <button onClick={prevPage}>{'<'}</button>
          <span>{`${currentPage}/${Math.ceil(filteredTodos.length / todosPerPage)}`}</span>
          <button onClick={nextPage}>{'>'}</button>
        </div>
      </div>
      <div className="separator"></div>
      <TodoList
        todos={currentTodos}
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
