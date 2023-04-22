
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Discharge")
public class Discharge extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Discharge() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double total = 0;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PrintWriter out = response.getWriter();
        try {
            c = GetConnection.getConnection();
            String pid = request.getParameter("pid");
            int days = Integer.parseInt(request.getParameter("days"));
            int daycost = Integer.parseInt(request.getParameter("daycost"));
            String medData = request.getParameter("mc");
            String[] mcs = medData.split(";");
            String sq1 = "DELETE FROM patient WHERE pid = ?";
            ps = c.prepareStatement(sq1);
            ps.setString(1, pid);
            ps.executeUpdate();
            String sq2 = "SELECT name, price FROM medicine WHERE mid = ?";
            ps = c.prepareStatement(sq2);
            out.println("<h1 align='center' style='font-family: Arial, sans-serif;'>Patient Bill</h1>");
            out.println("<hr>");
            out.println("<table style='width:100%'>");
            out.println("<tr style='font-family: Arial, sans-serif;'><th>Medicine Name</th><th>Quantity</th><th>Price</th><th>Total</th></tr>");
            for (String mc : mcs) {
                String mid = mc.split(",")[0];
                int count = Integer.parseInt(mc.split(",")[1]);
                ps.setString(1, mid);
                rs = ps.executeQuery();
                rs.next();
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                double itemTotal = price * count;
                total += itemTotal;
                out.println("<tr style='font-family: Arial, sans-serif;'><td>" + name + "</td><td>" + count + "</td><td>" + price + "</td><td>" + itemTotal + "</td></tr>");
            }
            double stayTotal = days * daycost;
            total += stayTotal;
            out.println("<tr style='font-family: Arial, sans-serif;'><td>Stay Cost</td><td></td><td>" + daycost + "</td><td>" + stayTotal + "</td></tr>");
            out.println("</table>");
            out.println("<hr>");
            out.println("<h2 align='right' style='font-family: Arial, sans-serif;'>Total: " + total + "</h2>");
        } catch (SQLException e) {
            out.println("<h1 align='center' style='font-family: Arial, sans-serif;'>An error occurred while processing the bill. Please try again later.</h1>");
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
//            if (c != null) {
//                try {
//                    c.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }

}
