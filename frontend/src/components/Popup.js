import React, { useState } from 'react';
import '../App.css'; // Popup stilinin de burada olduğunu varsayarak App.css'i import ediyoruz

function Popup({ task, endDate, onSave, onClose }) {
  const [newTask, setNewTask] = useState(task);
  const [newEndDate, setNewEndDate] = useState(endDate);

  const handleSave = () => {
    onSave(newTask, newEndDate);
    onClose();
  };

  return (
    <div className="popup">
      <div className="popup-inner">
        <input
          type="text"
          value={newTask}
          onChange={(e) => setNewTask(e.target.value)}
        />
        <input
          type="date"
          value={newEndDate}
          onChange={(e) => setNewEndDate(e.target.value)}
        />
        <div className="button-container">
          <button className="save-button" onClick={handleSave}>Save</button>
          <button className="cancel-button" onClick={onClose}>Cancel</button>
        </div>
      </div>
    </div>
  );
}

export default Popup;
