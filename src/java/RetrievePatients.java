import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RetrievePatients")
public class RetrievePatients extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public RetrievePatients() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {

            Connection c = GetConnection.getConnection();
            String sql = "select * from patient";

            Statement s = c.createStatement();
            ResultSet r = s.executeQuery(sql);

            response.setContentType("text/html");
            ResultSetMetaData rms = r.getMetaData();

            out.println("<table>");
            out.println("<td>");
            out.println("<th>" + rms.getColumnName(1) + "</th>");
            out.println("<th>" + rms.getColumnName(2) + "</th>");
            out.println("<th>" + rms.getColumnName(3) + "</th>");
            out.println("<th>" + rms.getColumnName(4) + "</th>");
            out.println("<th>" + rms.getColumnName(5) + "</th>");
            out.println("<th>" + rms.getColumnName(6) + "</th>");
            out.println("<th>" + rms.getColumnName(7) + "</th>");
            out.println("<th>" + rms.getColumnName(8) + "</th>");
            out.println("<th>" + rms.getColumnName(9) + "</th>");
            out.println("<th>" + rms.getColumnName(10) + "</th>");
            out.println("<th>" + rms.getColumnName(11) + "</th>");
            out.println("</td>");
            while (r.next()) {
                out.println("<tr>");
                out.println("<td></td>");
                out.println("<td>" + r.getString("pid") + "</td>");
                out.println("<td>" + r.getString("name") + "</td>");
                out.println("<td>" + r.getString("email") + "</td>");
                out.println("<td>" + r.getString("phone") + "</td>");
                out.println("<td>" + r.getString("age") + "</td>");
                out.println("<td>" + r.getString("gender") + "</td>");
                out.println("<td>" + r.getString("blood") + "</td>");
                out.println("<td>" + r.getString("visited") + "</td>");
                out.println("<td>" + r.getString("symptom") + "</td>");
                out.println("<td>" + r.getString("disease") + "</td>");
                out.println("<td>" + r.getString("doctor") + "</td>");
                out.println("</tr>");
            }
            out.print("</table>");
        } catch (SQLException e) {
            response.setContentType("text/html");
            out.println("<br><br><br><h1 align='center' style='font-family: Arial, sans-serif;'><font color=\"red\">TRY AGAIN<br></font></h1><script type=\"text/javascript\">");
            out.println("redirectURL = \"welcome.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");
            out.println("</script>");
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
