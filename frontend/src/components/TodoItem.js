import React, { useState } from 'react';
import Popup from './Popup';
import editIcon from '../icons/edit.svg';
import deleteIcon from '../icons/delete.svg';
import '../TodoItem.css';  // CSS dosyasını buraya ekleyin

function TodoItem({ todo, toggleComplete, deleteTodo, editTodo }) {
  const [isEditing, setIsEditing] = useState(false);

  const handleEdit = (newTask, newEndDate) => {
    editTodo(todo.id, newTask, newEndDate);
    setIsEditing(false);
  };

  return (
    <li className={`todo-item ${todo.completed ? 'completed' : ''}`}>
      <input
        type="checkbox"
        checked={todo.completed}
        onChange={() => toggleComplete(todo.id)}
      />
      <div className="todo-text">
        <span>{todo.name}</span>
        <span className="todo-date">{todo.endDate}</span>
      </div>
      <div>
        <button className="icon-button edit" onClick={() => setIsEditing(true)}>
          <img src={editIcon} alt="Edit" />
        </button>
        <button className="icon-button delete" onClick={() => deleteTodo(todo.id)}>
          <img src={deleteIcon} alt="Delete" />
        </button>
      </div>
      {isEditing && (
        <Popup
          task={todo.name}
          endDate={todo.endDate}
          onClose={() => setIsEditing(false)}
          onSave={handleEdit}
        />
      )}
    </li>
  );
}

export default TodoItem;
