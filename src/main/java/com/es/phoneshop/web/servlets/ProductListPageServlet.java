package com.es.phoneshop.web.servlets;

import com.es.phoneshop.service.ArrayListProductService;
import com.es.phoneshop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductListPageServlet extends HttpServlet {

    ProductService productService = new ArrayListProductService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", productService.findProducts());
        request.getRequestDispatcher("/WEB-INF/servlets/productList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("userPhoneSearchInput") != null) {
            String userSearchModelInput = request.getParameter("userPhoneSearchInput");
            request.setAttribute("products", productService.findProductsByDescription(userSearchModelInput));
        }

        if (request.getParameter("descriptionSort") != null) {
            int sort = Integer.valueOf(request.getParameter("descriptionSort"));
            if (sort == 1) {
                request.setAttribute("products", productService.sortByDescription());
            } else {
                request.setAttribute("products", productService.sortByDescriptionReversed());
            }
        }

        if (request.getParameter("priceSort") != null) {
            int sort = Integer.valueOf(request.getParameter("priceSort"));
            if (sort == 1) {
                request.setAttribute("products", productService.sortByPrice());
            } else {
                request.setAttribute("products", productService.sortByPriceReversed());
            }
        }

        request.getRequestDispatcher("/WEB-INF/servlets/productList.jsp").forward(request, response);
    }
}
