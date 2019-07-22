package com.es.phoneshop.web.servlets;

import com.es.phoneshop.service.ArrayListProductService;
import com.es.phoneshop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductDetailsPageServlet extends HttpServlet {

    ProductService productService = new ArrayListProductService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productCode = request.getParameter("productCode");
        System.out.println(productCode);
        String productDetails = productService.getProductDetailsByCode(productCode);
        if (productDetails == null) {
            request.setAttribute("productCode", productCode);
            request.getRequestDispatcher("/WEB-INF/servlets/errorPage.jsp").forward(request, response);
        } else {
            request.setAttribute("productDetails", productDetails);
            request.getRequestDispatcher("/WEB-INF/servlets/productDetails.jsp").forward(request, response);
        }
    }
}

