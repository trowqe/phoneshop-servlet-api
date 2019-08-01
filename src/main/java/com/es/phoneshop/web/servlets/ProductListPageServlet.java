package com.es.phoneshop.web.servlets;

import com.es.phoneshop.service.ProductServiceImpl;
import com.es.phoneshop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductListPageServlet extends HttpServlet {

    ProductService productService = new ProductServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //  request.setAttribute("products", productService.);
        request.getRequestDispatcher("/WEB-INF/servlets/productList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/servlets/productList.jsp").forward(request, response);
    }
}
