
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Cf", urlPatterns = {
    "/cf"
})
public class Cf extends HttpServlet {
    protected void processRequest(
        HttpServletRequest request,
        HttpServletResponse response
    )
    throws ServletException,
    IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()) {
            String paramvMin = request.getParameter("vmin");
            int min = (paramvMin == null || paramvMin.isEmpty()) ? -100 :
Integer.parseInt(paramvMin);

            String paramvMax = request.getParameter("vmax");
            int max = (paramvMax == null || paramvMax.isEmpty()) ? 100 : Integer.parseInt(paramvMax);

            String paramInc = request.getParameter("vinc");
            int inc = (paramInc == null || paramInc.isEmpty()) ? 5 :Integer.parseInt(paramInc);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("\t<head>");
            out.println("\t\t<meta http-equiv=\"Content-Type\"content=\"ext/html; charset=UTF-8\">");
            out.println("\t\t<title>Calculadora</title>");
            out.println("\t\t<link rel=\"stylesheet\" type=\"text/css\"href =\"estilo.css\">");
            out.println("\t</head>");
            out.println("\t<body>");
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<th>Celsius</th>");
            out.println("<th>Fahrenheit</th>");
            out.println("</tr>");
            for (int celsius = min; celsius <= max; celsius += inc) {
            double fahr = 1.8 * celsius + 32;
            out.println("<tr>");
            out.println("<td>" + celsius + "</td>");
            out.println("<td>" + fahr + "</td>");
            out.println("</tr>");
            }
            out.println("</table>");
                        out.println("\t</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException,
    IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException,
    IOException {
        processRequest(request, response);
    }
}
