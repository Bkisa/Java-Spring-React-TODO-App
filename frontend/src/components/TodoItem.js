import React, { useState } from 'react';
import editIcon from '../icons/edit.svg';
import deleteIcon from '../icons/delete.svg';
import Popup from './Popup';

function TodoItem({ todo, toggleComplete, deleteTodo, editTodo }) {
  const [showPopup, setShowPopup] = useState(false);

  const handleEditClick = () => {
    setShowPopup(true);
  };

  const handleClosePopup = () => {
    setShowPopup(false);
  };

  const handleSavePopup = (updatedTodo) => {
    editTodo(updatedTodo.id, updatedTodo.name);
    setShowPopup(false);
  };

  return (
    <div className={`todo-item ${todo.completed ? 'completed' : ''}`}>
      <input
        type="checkbox"
        checked={todo.completed}
        onChange={() => toggleComplete(todo.id)}
        className="checkbox"
      />
      <span className="todo-name">{todo.name}</span>
      <button className="icon-button edit" onClick={handleEditClick}>
        <img src={editIcon} alt="Edit" />
      </button>
      <button className="icon-button delete" onClick={() => deleteTodo(todo.id)}>
        <img src={deleteIcon} alt="Delete" />
      </button>
      {showPopup && 
        <Popup 
          todo={todo} 
          onClose={handleClosePopup} 
          onSave={handleSavePopup} 
        />
      }
    </div>
  );
}

export default TodoItem;
