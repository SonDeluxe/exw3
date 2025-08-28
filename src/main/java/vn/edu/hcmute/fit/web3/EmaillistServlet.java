package vn.edu.hcmute.fit.web3;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String dobString = request.getParameter("dob");
        String hearAbout = request.getParameter("hearAbout");
        String receiveCds = request.getParameter("receiveCds");
        String receiveEmails = request.getParameter("receiveEmails");
        String contactBy = request.getParameter("contactBy");

        String errorMessage = "";

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
                LocalDate dob = LocalDate.parse(dobString);
            }
        } catch (DateTimeParseException e) {
            errorMessage += "<li>Ngày sinh không hợp lệ! Vui lòng nhập định dạng YYYY-MM-DD.</li>";
        }

        if (!errorMessage.isEmpty()) {
            displayErrorPage(response, errorMessage);
            return;
        }

        if (receiveCds == null) {
            receiveCds = "No";
        } else {
            receiveCds = "Yes";
        }

        if (receiveEmails == null) {
            receiveEmails = "No";
        } else {
            receiveEmails = "Yes";
        }

        displaySuccessPage(response, firstName, lastName, email, dobString, hearAbout, receiveCds, receiveEmails, contactBy);
    }

    private void displaySuccessPage(HttpServletResponse response, String firstName, String lastName, String email, String dobString, String hearAbout, String receiveCds, String receiveEmails, String contactBy)
            throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"vi\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");
        out.println("<title>Kết quả khảo sát</title>");
        out.println("<link rel=\"stylesheet\" href=\"styles/main.css\" />");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"topbar\"></div>");
        out.println("<header class=\"brand\"><img src=\"images/murach-logo.png\" alt=\"Murach logo\"></header>");
        out.println("<main class=\"container\">");
        out.println("<h1>Cảm ơn bạn đã tham gia khảo sát!</h1>");
        out.println("<p class=\"lead\">Đây là thông tin bạn đã cung cấp:</p>");

        out.println("<table>");
        out.println("<thead><tr><th>Thông tin</th><th>Chi tiết</th></tr></thead>");
        out.println("<tbody>");
        out.println("<tr><td><strong>Họ</strong></td><td>" + lastName + "</td></tr>");
        out.println("<tr><td><strong>Tên</strong></td><td>" + firstName + "</td></tr>");
        out.println("<tr><td><strong>Email</strong></td><td>" + email + "</td></tr>");
        out.println("<tr><td><strong>Ngày sinh</strong></td><td>" + (dobString != null && !dobString.trim().isEmpty() ? dobString : "Không có") + "</td></tr>");
        out.println("<tr><td><strong>Bạn biết đến chúng tôi qua</strong></td><td>" + hearAbout + "</td></tr>");
        out.println("<tr><td><strong>Nhận thông báo về CD</strong></td><td>" + receiveCds + "</td></tr>");
        out.println("<tr><td><strong>Nhận thông báo qua email</strong></td><td>" + receiveEmails + "</td></tr>");
        out.println("<tr><td><strong>Phương thức liên lạc ưu tiên</strong></td><td>" + contactBy + "</td></tr>");
        out.println("</tbody>");
        out.println("</table>");

        out.println("</main>");
        out.println("</body>");
        out.println("</html>");
    }

    private void displayErrorPage(HttpServletResponse response, String errorMessage)
            throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"vi\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");
        out.println("<title>Lỗi</title>");
        out.println("<link rel=\"stylesheet\" href=\"styles/main.css\" />");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"topbar\"></div>");
        out.println("<header class=\"brand\"><img src=\"images/murach-logo.png\" alt=\"Murach logo\"></header>");
        out.println("<main class=\"container\">");
        out.println("<h1>Đã xảy ra lỗi!</h1>");
        out.println("<p class=\"lead\">Vui lòng sửa các lỗi sau và thử lại:</p>");
        out.println("<ul class=\"error-list\">" + errorMessage + "</ul>");
        out.println("<p><a href=\"/\">Quay lại trang khảo sát</a></p>");
        out.println("</main>");
        out.println("</body>");
        out.println("</html>");
    }
}