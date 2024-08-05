import React, { useState } from 'react';
import '../App.css'; // Ensure App.css is included for consistent styling

function TodoInput({ addTodo }) {
  const [task, setTask] = useState('');
  const [endDate, setEndDate] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (task.trim() && endDate.trim()) {
      addTodo(task, endDate);
      setTask('');
      setEndDate('');
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
      <input
        type="date"
        value={endDate}
        onChange={(e) => setEndDate(e.target.value)}
        placeholder="End Date"
      />
      <button type="submit">Add new task</button>
    </form>
  );
}

export default TodoInput;
