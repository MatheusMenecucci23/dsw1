package br.ufscar.dc.dsw.servlet;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

@WebServlet(urlPatterns = { "/mudaLingua" })
public class MudaLinguaServlet extends HttpServlet {
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String language = request.getParameter("lingua");
        Locale locale = new Locale(language);
        Config.set(request.getSession(), Config.FMT_LOCALE, locale);
        Config.set(request.getSession(), Config.FMT_FALLBACK_LOCALE, locale);
        response.sendRedirect("index.jsp");
    } 
}
