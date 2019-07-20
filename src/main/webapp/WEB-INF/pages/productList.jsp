<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>
<tags:master pageTitle="Product List">
    <p>
        Welcome to Expert-Soft training!
    </p>

    <!--<form action=""></form>-->
    <form method="post" action="products">
        <input type="text" name="userPhoneSearchInput" placeholder="Type phone model ...">
        <button type="submit" name="search">Search</button>
    </form>

    <table>
        <thead>
        <tr>
            <td>Image</td>
            <td>
                Description

                <form method="post" action="products">
                    <input type="radio" name="descriptionSort" value="0"/>a-z
                    <input type="radio" name="descriptionSort" value="1"/>z-a
                    <button type="submit" name="sort">sort</button>
                </form>

            </td>
            <td class="price">
                Price

                <form method="post" action="products">
                    <input type="radio" name="priceSort" value="0"/>cheap
                    <input type="radio" name="priceSort" value="1"/>expensive
                    <button type="submit" name="sort">sort</button>
                </form>

            </td>
        </tr>
        </thead>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>
                    <img class="product-tile"
                         src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
                </td>
                <td>${product.description}</td>
                <td class="price">
                    <fmt:formatNumber value="${product.price}" type="currency"
                                      currencySymbol="${product.currency.symbol}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</tags:master>