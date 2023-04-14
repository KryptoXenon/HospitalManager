import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RetrieveDoctor")
public class RetrieveDoctor extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public RetrieveDoctor() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {

            Connection c = GetConnection.getConnection();
            String sql = "select * from doctor";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.addBatch();

            ResultSet r = ps.executeQuery();
            ResultSetMetaData rms = r.getMetaData();
            ps.clearBatch();

            out.println("<table>");
            out.println("<td>");
            out.println("<th>" + "ID" + "</th>");
            out.println("<th>" + "Name" + "</th>");
            out.println("<th>" + "Email" + "</th>");
            out.println("<th>" + "Phone" + "</th>");
            out.println("<th>" + "Age" + "</th>");
            out.println("<th>" + "JoinDate" + "</th>");
            out.println("<th>" + "Salary" + "</th>");
            out.println("<th>" + "Specialist" + "</th>");
            out.println("<th>" + "Patients" + "</th>");
            out.println("<th>Patients List</th>");
            out.println("</td>");
            while (r.next()) {
                out.println("<tr>");
                out.println("<td></td>");
                out.println("<td>" + r.getString("did") + "</td>");
                out.println("<td>" + r.getString("name") + "</td>");
                out.println("<td>" + r.getString("email") + "</td>");
                out.println("<td>" + r.getString("phone") + "</td>");
                out.println("<td>" + r.getString("age") + "</td>");
                out.println("<td>" + r.getString("joindate") + "</td>");
                out.println("<td>" + r.getString("salary") + "</td>");
                out.println("<td>" + r.getString("specialist") + "</td>");
                out.println("<td>" + r.getString("patients") + "</td>");
                out.println("<td>" + "<form method=\"GET\" action=\"RetrievePatientsDID\"><input hidden value =\"" + r.getString("did") + "\" name=\"did\"><input type=\"submit\" value=\"Click\"></form>");
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
