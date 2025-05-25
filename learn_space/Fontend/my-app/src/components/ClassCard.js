import React from "react";
import "./ClassCard.css";

function ClassCard({ title, teacher, avatar, color }) {
  return (
    <div className="class-card" style={{ background: color }}>
      <div className="class-title">{title}</div>
      <div className="class-teacher">
        <span>{teacher}</span>
        <img className="avatar" src={avatar} alt="avatar" />
      </div>
      <div className="class-actions">
        <span>📷</span>
        <span>📁</span>
      </div>
    </div>
  );
}

export default ClassCard;