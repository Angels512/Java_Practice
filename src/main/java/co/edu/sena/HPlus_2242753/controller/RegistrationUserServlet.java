package co.edu.sena.HPlus_2242753.controller;

import co.edu.sena.HPlus_2242753.model.beans.Product;
import co.edu.sena.HPlus_2242753.model.beans.User;
import co.edu.sena.HPlus_2242753.model.dao.ApplicationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

@WebServlet("/registerUser")
public class RegistrationUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Colectar informacion del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String fr = request.getParameter("fname");
        String firstName = fr.toUpperCase();

        String ln = request.getParameter("lname");
        String lastName = ln.toUpperCase();


        String activity = request.getParameter("activity");
        int age = Integer.parseInt(request.getParameter("age"));


        // Crear y llenar objetos Userbean
        User user = new User(password, username, firstName, lastName, age, activity);


        // Llamar al DAO y guardar el objeto USER en la BD
        ApplicationDao dao = new ApplicationDao();
        int rows = dao.registerUser(user);


        // Preparar mensaje sobre la operacion
        String infoMessage = null;
        if (rows == 0)
        {
            infoMessage = "Lo siento, ocurrio un error.";
        }else{
            infoMessage = "Registro exitoso!";
        }


        // Escribir el mensaje y enviarlo al navegador
        String page = getHTMLString(request.getServletContext().getRealPath("/_html/register.html"), infoMessage);
        response.getWriter().write(page);

    } // Fin doPost

    public String getHTMLString(String filePath, String message) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line="";
        StringBuffer buffer = new StringBuffer();

        while((line=reader.readLine())!=null)
        {
            buffer.append(line);
        }

        reader.close();
        String page = buffer.toString();

        page = MessageFormat.format(page, message);

        return page;
    } // Fin getHTMLString


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = getHTMLString(request.getServletContext().getRealPath("/_html/register.html"), "");
        response.getWriter().write(page);
    }
} // Fin RegistrationUserServlet
