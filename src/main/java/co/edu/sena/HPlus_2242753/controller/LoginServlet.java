package co.edu.sena.HPlus_2242753.controller;

import co.edu.sena.HPlus_2242753.model.dao.ApplicationDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String html = "<html><h3>Por favor inicie sesion</h3></html>";
        response.getWriter().write(html+" ");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/_html/login.jsp");
        dispatcher.include(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Obtener datos del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Llamar al DAO para la validacion
        ApplicationDao dao = new ApplicationDao();
        boolean isValidUser = dao.validateUser(username, password);

        // Verificar si el usuario el invalido para mostrar un error en pantalla
        if(isValidUser)
        {
            // Requerir el redireccionamiento
            request.getRequestDispatcher("/index.html").forward(request, response);
        }else {
            String errorMessage = "Credenciales invalidas, por favor intente de nuevo!";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("/_html/login.jsp").forward(request, response);
        }
    }
}
