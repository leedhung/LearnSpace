import React from "react";
import ClassCard from "./ClassCard";
import "./ClassGrid.css";

const classes = [
  {
    title: "PTTK_T5_RD200",
    teacher: "Như Quỳnh Trần Lê",
    avatar: "https://randomuser.me/api/portraits/men/1.jpg",
    color: "linear-gradient(135deg, #009688, #43cea2)",
  },
  {
    title: "ML_2425_T7_456",
    teacher: "Dũ Nguyễn Văn",
    avatar: "https://randomuser.me/api/portraits/men/2.jpg",
    color: "linear-gradient(135deg, #607d8b, #90a4ae)",
  },
  {
    title: "Nhập Môn CNPM 2025",
    teacher: "Công Song Nguyễn Đức",
    avatar: "https://randomuser.me/api/portraits/men/3.jpg",
    color: "linear-gradient(135deg, #ff7043, #ffa726)",
  },
  {
    title: "Py_2425",
    teacher: "Dũ Nguyễn Văn",
    avatar: "https://randomuser.me/api/portraits/men/4.jpg",
    color: "linear-gradient(135deg, #607d8b, #90a4ae)",
  },
  {
    title: "AI_2425",
    teacher: "Dũ Nguyễn Văn",
    avatar: "https://randomuser.me/api/portraits/men/5.jpg",
    color: "linear-gradient(135deg, #009688, #43cea2)",
  },
  {
    title: "Hệ Quản Trị CSDL",
    teacher: "Như Quỳnh Trần Lê",
    avatar: "https://randomuser.me/api/portraits/men/6.jpg",
    color: "linear-gradient(135deg, #1976d2, #2196f3)",
  },
  {
    title: "NM CSDL THU3 2024",
    teacher: "Như Quỳnh Trần Lê",
    avatar: "https://randomuser.me/api/portraits/men/7.jpg",
    color: "linear-gradient(135deg, #607d8b, #90a4ae)",
  },
  {
    title: "2024_OOP",
    teacher: "Nga Trần Thị Thanh",
    avatar: "https://randomuser.me/api/portraits/women/1.jpg",
    color: "linear-gradient(135deg, #607d8b, #90a4ae)",
  },
];

function ClassGrid({ onSelectClass }) {
  return (
    <div className="class-grid">
      {classes.map((cls, idx) => (
        <div key={idx} onClick={() => onSelectClass(cls)} style={{ cursor: "pointer" }}>
          <ClassCard {...cls} />
        </div>
      ))}
    </div>
  );
}

export default ClassGrid;
