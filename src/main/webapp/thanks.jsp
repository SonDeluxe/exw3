<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <title>Cảm ơn</title>
    <link rel="stylesheet" href="styles/main.css"/>
</head>
<body>
<main class="container">
    <h1>Cảm ơn bạn đã tham gia khảo sát</h1>
    <p>Đây là thông tin mà bạn đã cung cấp:</p>
    <ul>
        <li>Tên: ${firstName}</li>
        <li>Họ: ${lastName}</li>
        <li>Email: ${email}</li>
        <li>Ngày sinh: ${dob}</li>
        <li>Biết đến chúng tôi qua: ${hearAbout}</li>
        <li>Nhận thông báo về CD: ${receiveCds}</li>
        <li>Nhận thông báo qua email: ${receiveEmails}</li>
        <li>Phương thức liên lạc ưu tiên: ${contactBy}</li>
    </ul>
    <p><a href="index.html">Quay lại trang khảo sát</a></p>
</main>
</body>
    </html>