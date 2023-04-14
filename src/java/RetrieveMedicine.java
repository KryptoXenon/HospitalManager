import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RetrieveMedicine")
public class RetrieveMedicine extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public RetrieveMedicine() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {

            Connection c = GetConnection.getConnection();
            String sql = "select * from medicine";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.addBatch();

            ResultSet r = ps.executeQuery();
            ResultSetMetaData rms = r.getMetaData();
            ps.clearBatch();

            out.println("<table>");
            out.println("<td>");
            out.println("<th>" + rms.getColumnName(1) + "</th>");
            out.println("<th>" + rms.getColumnName(2) + "</th>");
            out.println("<th>" + rms.getColumnName(3) + "</th>");
            out.println("<th>" + rms.getColumnName(4) + "</th>");
            out.println("</td>");
            while (r.next()) {
                out.println("<tr>");
                out.println("<td></td>");
                out.println("<td>" + r.getString("mid") + "</td>");
                out.println("<td>" + r.getString("name") + "</td>");
                out.println("<td>" + r.getString("price") + "</td>");
                out.println("<td>" + r.getString("count") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            response.setContentType("text/html");
            out.println("<br><br><br><h1 align='center' style='font-family: Arial, sans-serif;'><font color=\"red\">TRY AGAIN<br></font></h1>");
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

}
