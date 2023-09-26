package br.ufscar.dc.dsw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ValidaServlet", urlPatterns = {"/valida"})
public class ValidaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            try {
                String paramvMin = request.getParameter("op1");
                int op1 = (paramvMin == null || paramvMin.isEmpty()) ? -100 :Integer.parseInt(paramvMin);

                String paramvMax = request.getParameter("op2");
                int op2 = (paramvMax == null || paramvMax.isEmpty()) ? 100 : Integer.parseInt(paramvMax);

                String paramInc = request.getParameter("op3");
                int op3 = (paramInc == null || paramInc.isEmpty()) ? 5 :Integer.parseInt(paramInc);
                
                if(op1 == 0 && op2 == 0 && op3 == 0){
                    op3 = 1;
                }

                request.setAttribute("op1", op1);
                request.setAttribute("op2", op2);
                request.setAttribute("op3", op3);
                request.getRequestDispatcher("/conversationcf.jsp").forward(request, response);

            } catch(NumberFormatException e) {
                response.sendRedirect("erro.html");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}