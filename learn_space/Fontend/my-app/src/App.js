import React, { useState } from "react";
import Header from "./components/Header";
import Sidebar from "./components/Sidebar";
import ClassGrid from "./components/ClassGrid";
import ClassDetail from "./components/ClassDetail";
import "./App.css";

function App() {
  const [selectedClass, setSelectedClass] = useState(null);

  return (
    <div className="app-container">
      <Header />
      <div className="main-content">
        <Sidebar />
        <div style={{ flex: 1 }}>
          {!selectedClass ? (
            <ClassGrid onSelectClass={setSelectedClass} />
          ) : (
            <ClassDetail classInfo={selectedClass} onBack={() => setSelectedClass(null)} />
          )}
        </div>
      </div>
    </div>
  );
}

export default App;