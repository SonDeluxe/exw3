<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Lỗi Java</title>
    <link rel="stylesheet" href="styles/main.css">
</head>
<body>
<h1>Lỗi Java</h1>
<p>Xin lỗi, đã xảy ra một lỗi trong quá trình xử lý yêu cầu.</p>
<p>Vui lòng nhấn nút Quay lại trên trình duyệt để tiếp tục.</p>

<h2>Chi tiết</h2>
<p>Loại lỗi: <strong><%= exception.getClass().getName() %></strong></p>
<p>Thông điệp: <strong><%= exception.getMessage() %></strong></p>
</body>
</html>