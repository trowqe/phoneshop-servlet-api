package com.es.phoneshop.web;

import com.es.phoneshop.dao.ArrayListProductDao;
import com.es.phoneshop.dao.ProductDao;
import com.es.phoneshop.service.ArrayListProductService;
import com.es.phoneshop.service.ProductService;
import com.es.phoneshop.web.servlets.ProductListPageServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductListPageServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    private ProductListPageServlet servlet = new ProductListPageServlet();

    @Test
    public void whenCallDoGetTheServletReturnProductListJsp() throws ServletException, IOException {
        String path = "/WEB-INF/servlets/productList.jsp";


        when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);

        servlet.init();
        servlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }


}