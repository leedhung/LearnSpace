import React, { useState } from "react";
import LectureCard from "./LectureCard";

function ClassDetail({ classInfo, onBack }) {
  // Quản lý trạng thái mở form thông báo
  const [showForm, setShowForm] = useState(false);
  const [lectureTitle, setLectureTitle] = useState("");
  const [lectureContent, setLectureContent] = useState("");
  const [lectureFiles, setLectureFiles] = useState([]);
  const [lessons, setLessons] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  if (!classInfo) return null;

  const handleCreateLesson = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    const formData = new FormData();
    formData.append("authorId", String(3));
    formData.append("classId", String(classInfo.id || 1));
    formData.append("content", lectureContent);
    formData.append("creatAt", new Date().toISOString().slice(0, 10)); // yyyy-MM-dd
    formData.append("name", lectureTitle);
    if (lectureFiles && lectureFiles.length > 0) {
      for (let i = 0; i < lectureFiles.length; i++) {
        formData.append("files", lectureFiles[i]);
      }
    }

    for (let pair of formData.entries()) {
      console.log(pair[0]+ ':', pair[1]);
    }

    try {
      const res = await fetch("http://localhost:8088/lesson/create-lesson", {
        method: "POST",
        body: formData,
      });
      const data = await res.json();
      console.log('RESPONSE:', data);
      if (data.result) {
        setLessons([data.result, ...lessons]);
        setShowForm(false);
        setLectureTitle("");
        setLectureContent("");
        setLectureFiles([]);
      } else {
        alert("Tạo bài học thất bại!");
      }
    } catch (err) {
      alert("Lỗi kết nối server!");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div style={{
      display: "flex",
      flexDirection: "column",
      alignItems: "center",
      padding: "40px 0",
      position: "relative"
    }}>
      {isLoading && (
        <div style={{
          position: "fixed",
          top: 0,
          left: 0,
          right: 0,
          bottom: 0,
          background: "rgba(0, 0, 0, 0.5)",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          zIndex: 1000
        }}>
          <div style={{
            background: "white",
            padding: "20px 40px",
            borderRadius: "8px",
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            gap: "16px"
          }}>
            <div style={{
              width: "40px",
              height: "40px",
              border: "4px solid #f3f3f3",
              borderTop: "4px solid #1976d2",
              borderRadius: "50%",
              animation: "spin 1s linear infinite"
            }}></div>
            <div>Đang xử lý...</div>
          </div>
        </div>
      )}
      <style>
        {`
          @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
          }
        `}
      </style>
      <button
        onClick={onBack}
        style={{
          marginBottom: 24,
          alignSelf: "flex-start",
          marginLeft: 32,
          background: "#fff",
          border: "1px solid #ccc",
          borderRadius: 4,
          padding: "6px 16px",
          cursor: "pointer"
        }}
      >
        ← Quay lại
      </button>
      {/* Poster/banner */}
      <div style={{
        width: 900,
        maxWidth: "95vw",
        height: 180,
        background: classInfo.color,
        borderRadius: 12,
        marginBottom: 32,
        display: "flex",
        alignItems: "flex-end",
        paddingLeft: 40,
        fontSize: 36,
        fontWeight: "bold",
        color: "#fff",
        letterSpacing: 1
      }}>
        {classInfo.title}
      </div>
      {/* Thẻ bài giảng */}
      <div style={{ width: 900, maxWidth: "95vw" }}>
        {/* Thẻ bài giảng đầu tiên */}
        <LectureCard
          style={{ padding: showForm ? 0 : "24px 32px" }}
        >
          {!showForm ? (
            <div
              style={{
                display: "flex",
                alignItems: "center",
                gap: 16,
                cursor: "pointer"
              }}
              onClick={() => setShowForm(true)}
            >
              <div style={{
                width: 48,
                height: 48,
                borderRadius: "50%",
                background: "#1976d2",
                color: "#fff",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                fontWeight: "bold",
                fontSize: 22
              }}>H</div>
              <span style={{ color: "#888" }}>Thông báo nội dung nào đó cho lớp học của bạn</span>
            </div>
          ) : (
            <form onSubmit={handleCreateLesson} style={{ padding: "24px 32px" }}>
              <input
                type="text"
                placeholder="Tên bài giảng"
                value={lectureTitle}
                onChange={e => setLectureTitle(e.target.value)}
                required
                style={{ 
                  width: "100%", 
                  marginBottom: 12,
                  padding: "8px",
                  border: "1px solid #ccc",
                  borderRadius: "4px"
                }}
              />
              <input
                type="text"
                placeholder="Thông báo nội dung nào đó cho lớp học của bạn"
                value={lectureContent}
                onChange={e => setLectureContent(e.target.value)}
                required
                style={{ 
                  width: "100%", 
                  marginBottom: 16,
                  padding: "8px",
                  border: "1px solid #ccc",
                  borderRadius: "4px"
                }}
              />
              <input
                type="file"
                multiple
                onChange={e => setLectureFiles(Array.from(e.target.files))}
                style={{ marginBottom: 16 }}
              />
              <div style={{ display: "flex", justifyContent: "flex-end", gap: 16 }}>
                <button type="button" onClick={() => setShowForm(false)}>Huỷ</button>
                <button type="submit" style={{ background: "#1976d2", color: "#fff" }}>Đăng</button>
              </div>
            </form>
          )}
        </LectureCard>
        {/* Các thẻ bài giảng khác bạn có thể thêm ở đây */}
        {lessons.map(lesson => (
          <LectureCard key={lesson.id}>
            <div style={{ display: "flex", alignItems: "center", gap: 12 }}>
              <div style={{
                width: 36, height: 36, borderRadius: "50%",
                background: "#0097a7", color: "#fff", display: "flex",
                alignItems: "center", justifyContent: "center", fontSize: 20
              }}>
                <span role="img" aria-label="icon">📝</span>
              </div>
              <div>
                <div style={{ fontWeight: "bold" }}>
                  Bài học: {lesson.name}
                </div>
                <div style={{ color: "#888", fontSize: 13 }}>
                  {lesson.creatAt}
                </div>
                <div>{lesson.content}</div>
              </div>
            </div>
          </LectureCard>
        ))}
      </div>
    </div>
  );
}

export default ClassDetail; 