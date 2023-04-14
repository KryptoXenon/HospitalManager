import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Logout")
public class Logout extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Logout() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        HttpSession sess = request.getSession();
        PrintWriter out = response.getWriter();

        sess.removeAttribute("name");
        sess.removeAttribute("aid");
        sess.invalidate();

        response.setContentType("text/html");
        out.println("<br><br><br><h1 align='center' style='font-family: Arial, sans-serif;'><font color=\"green\">YOU ARE LOGGED OUT OF THE SYSTEM<br>REDIRECTING YOU TO<br><br> HOMEPAGE</font></h1><script type=\"text/javascript\">");
        out.println("redirectURL = \"index.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");
        out.println("</script>");
    }

}
