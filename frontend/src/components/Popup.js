import React, { useState } from 'react';

function Popup({ todo, onClose, onSave }) {
  const [newName, setNewName] = useState(todo.name);

  const handleSave = () => {
    onSave({ ...todo, name: newName });
  };

  return (
    <div className="popup">
      <div className="popup-inner">
        <h2>Edit Todo</h2>
        <input 
          type="text" 
          value={newName} 
          onChange={(e) => setNewName(e.target.value)} 
        />
        <button onClick={handleSave}>OK</button>
        <button onClick={onClose}>Cancel</button>
      </div>
    </div>
  );
}

export default Popup;
