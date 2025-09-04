package vn.edu.hcmute.fit.web3;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EmaillistServlet", urlPatterns = {"/emaillist"})
public class EmaillistServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Yêu cầu 6: Thêm phương thức doGet để gọi doPost
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Yêu cầu 7: In giá trị của tham số "action" ra console để gỡ lỗi
        String action = request.getParameter("action");
        if (action != null) {
            System.out.println("Giá trị của action: " + action);
        }

        String url = "/thanks.jsp"; // Đường dẫn mặc định đến trang thành công
        String errorMessage = "";

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String dobString = request.getParameter("dob");
        String hearAbout = request.getParameter("hearAbout");
        String receiveCds = request.getParameter("receiveCds");
        String receiveEmails = request.getParameter("receiveEmails");
        String contactBy = request.getParameter("contactBy");

        // Kiểm tra dữ liệu đầu vào và thiết lập thông báo lỗi
        if (firstName == null || firstName.trim().isEmpty()) {
            errorMessage += "<li>Tên không được để trống!</li>";
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            errorMessage += "<li>Họ không được để trống!</li>";
        }
        if (email == null || email.trim().isEmpty()) {
            errorMessage += "<li>Email không được để trống!</li>";
        }

        try {
            if (dobString != null && !dobString.trim().isEmpty()) {
                LocalDate.parse(dobString);
            }
        } catch (DateTimeParseException e) {
            errorMessage += "<li>Ngày sinh không hợp lệ! Vui lòng nhập định dạng YYYY-MM-DD.</li>";
        }

        // Nếu có lỗi, chuyển hướng về trang index.html và truyền thông báo lỗi
        if (!errorMessage.isEmpty()) {
            url = "/index.html"; 
            request.setAttribute("errorMessage", errorMessage);
        }

        // Thiết lập các thuộc tính để truyền dữ liệu sang JSP
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("email", email);
        request.setAttribute("dob", dobString);
        request.setAttribute("hearAbout", hearAbout);
        request.setAttribute("receiveCds", receiveCds != null ? "Yes" : "No");
        request.setAttribute("receiveEmails", receiveEmails != null ? "Yes" : "No");
        request.setAttribute("contactBy", contactBy);
        
        // Chuyển tiếp yêu cầu đến trang JSP
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}