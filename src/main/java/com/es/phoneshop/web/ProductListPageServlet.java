package com.es.phoneshop.web;

import com.es.phoneshop.dao.ArrayListProductDao;
import com.es.phoneshop.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductListPageServlet extends HttpServlet {

    //it would be better to initialize productDao in init() ???
    private ProductDao productDao = ArrayListProductDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", productDao.findProducts());
        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("userPhoneSearchInput")!=null) {
            String userSearchModelInput = request.getParameter("userPhoneSearchInput");
            request.setAttribute("products", productDao.findProductsByDescription(userSearchModelInput));
        }

        if(request.getParameter("descriptionSort")!=null){
            int sort = Integer.valueOf(request.getParameter("descriptionSort"));
            if(sort==1){
                request.setAttribute("products", productDao.sortByDescription());
            }else{
                request.setAttribute("products", productDao.sortByDescriptionReversed());}
        }

        if(request.getParameter("priceSort")!=null){
            int sort = Integer.valueOf(request.getParameter("priceSort"));
            if(sort==1){
                request.setAttribute("products", productDao.sortByPrice()) ;
            }else{ request.setAttribute("products", productDao.sortByPriceReversed());}
        }



        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }
}
