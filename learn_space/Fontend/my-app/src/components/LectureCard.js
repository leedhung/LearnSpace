import React from "react";

function LectureCard({ children, style, ...props }) {
  return (
    <div
      style={{
        background: "#fff",
        borderRadius: 8,
        boxShadow: "0 2px 8px rgba(0,0,0,0.10)",
        marginBottom: 24,
        minHeight: 80,
        padding: "24px 32px",
        transition: "all 0.2s",
        ...style
      }}
      {...props}
    >
      {children}
    </div>
  );
}

export default LectureCard; 