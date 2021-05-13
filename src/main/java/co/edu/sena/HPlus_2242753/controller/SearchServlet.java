package co.edu.sena.HPlus_2242753.controller;

import co.edu.sena.HPlus_2242753.model.beans.Product;
import co.edu.sena.HPlus_2242753.model.dao.ApplicationDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;


@WebServlet(name = "SearchServlet", value = "/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Colectar el string de busqueda desde ek formulario search.html
        String searchString = request.getParameter("search");

        // Llamar el DAO para obtener los datos de la busqueda
        ApplicationDao dao = new ApplicationDao();
        List<Product> products = dao.searchProducts(searchString);

        // Escribir los productos en la pagina de resultados
        String page = getHTMLString(request.getServletContext().getRealPath("_html/searchResults.html"), products);
        response.getWriter().write(page);

    } // Fin doGet


    public String getHTMLString(String filePath, List<Product> products) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = "";
        StringBuffer buffer = new StringBuffer();

        while((line=reader.readLine()) != null)
        {
            buffer.append(line);
        }

        reader.close();
        String page = buffer.toString();

        page = MessageFormat.format(page, products.get(0).getProductImgPath(),
                products.get(1).getProductImgPath(),products.get(2).getProductImgPath(),
                products.get(0).getProductName(),products.get(1).getProductName(),
                products.get(2).getProductName(),0);

        return page;
    } // Fin getHTMLString
}
