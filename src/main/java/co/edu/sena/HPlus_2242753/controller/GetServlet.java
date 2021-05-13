package co.edu.sena.HPlus_2242753.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("ALL")
@WebServlet(name = "GetServlet", value = "/getservlet")
public class GetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String value = request.getParameter("name");
        String htmlReponse = "<html><h3>Welcome to Servlet!</h3></html>";
        PrintWriter writer = response.getWriter();
        writer.write(htmlReponse + " " + value);
    }
}
