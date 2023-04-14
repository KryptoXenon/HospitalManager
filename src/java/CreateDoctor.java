import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CreateDoctor")
public class CreateDoctor extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CreateDoctor() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
        PrintWriter out = response.getWriter();
        try {
            Connection c = GetConnection.getConnection();
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String age = request.getParameter("age");
            String joindate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String sal = request.getParameter("sal");
            String spec = request.getParameter("spec");
            String patients = "-1";    // initial is -1 always for every doctor
            String sql = "insert into doctor(name,email,phone,age,joindate,salary,specialist,patients) values(?,?,?,?,?,?,?,?)";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setInt(4, Integer.valueOf(age));
            ps.setString(5, joindate);
            ps.setLong(6, Long.valueOf(sal));
            ps.setString(7, spec);
            ps.setString(8, patients);
            ps.addBatch();

            int successCount = 0;
            successCount += ps.executeBatch()[0];
            ps.clearBatch();

            if (successCount == 1) {
                response.setContentType("text/html");
                out.println("<br><br><br><h1 align='center' style='font-family: Arial, sans-serif;'><font color=\"green\">SUCCESSFUL<br></font></h1><script type=\"text/javascript\">");
//				out.println("redirectURL = \"welcome.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");  
                out.println("</script>");
            } else {
                response.setContentType("text/html");
                out.println("<br><br><br><h1 align='center' style='font-family: Arial, sans-serif;'><font color=\"red\">THERE IS SOME PROBLEM<br></font></h1><script type=\"text/javascript\">");
//				out.println("redirectURL = \"welcome.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");  
                out.println("</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
