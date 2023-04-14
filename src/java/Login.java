import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Login")
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        Connection c = GetConnection.getConnection();
        String checkUserSql = "select * from assistant where email = ?";

        PreparedStatement ps;
        try {
            ps = c.prepareStatement(checkUserSql);
            ps.setString(1, email);
            ps.addBatch();
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getString("password").equals(pwd)) {
                HttpSession session = request.getSession();
                session.setAttribute("name", rs.getString("name"));
                session.setAttribute("aid", rs.getInt("aid"));
                response.sendRedirect("welcome.html");
            } else {

                response.setContentType("text/html");
                out.println("<br><br><br><h1 align='center' style='font-family: Arial, sans-serif;'><font color=\"red\">TRY AGAIN<br>REDIRECTING YOU TO LOGIN PAGE</font></h1><script type=\"text/javascript\">");
                out.println("redirectURL = \"login.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");
                out.println("</script>");
            }
            ps.clearBatch();
        } catch (SQLException e) {
            response.setContentType("text/html");
            out.println("<br><br><br><h1 align='center' style='font-family: Arial, sans-serif;'><font color=\"red\">TRY AGAIN<br>REDIRECTING YOU TO LOGIN PAGE</font></h1><script type=\"text/javascript\">");
            out.println("redirectURL = \"login.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");
            out.println("</script>");
            e.printStackTrace();
        }
    }

}
