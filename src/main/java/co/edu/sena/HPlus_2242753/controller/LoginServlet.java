package co.edu.sena.HPlus_2242753.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/_html/login.jsp");
        dispatcher.forward(request, response);
    }





    //    @Override
    //    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //
    //    }
}
