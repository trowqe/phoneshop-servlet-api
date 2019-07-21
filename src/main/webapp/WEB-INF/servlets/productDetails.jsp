<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:master pageTitle="Product Details">

    <jsp:include page="/WEB-INF/servlets/header.jsp"/>
    <p>
        Product Details page!
    </p>

   <br>
    <h1>${requestScope.productDetails}</h1>
    <jsp:include page="/WEB-INF/servlets/footer.jsp"/>
</tags:master>