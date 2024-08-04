import React, { useState } from 'react';

function TodoInput({ addTodo }) {
  const [task, setTask] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (task.trim()) {
      addTodo(task);
      setTask('');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>TodoInput</h2>
      <input
        type="text"
        value={task}
        onChange={(e) => setTask(e.target.value)}
        placeholder="New Todo"
      />
      <button type="submit">Add new task</button>
    </form>
  );
}

export default TodoInput;
