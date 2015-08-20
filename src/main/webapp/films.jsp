<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="filmsView" type="java.util.List"--%>
<%--@elvariable id="filmView" type="com.epam.irasov.filmlibrary.entity.Film"--%>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="site.films" bundle="${lang}"/>
    </title>
</head>
<body>
<div class="wrapper">
    <fl:header/>
    <fl:navigation/>
    <div class="container">
        <c:if test="${not empty filmsView}">
            <c:forEach items="${filmsView}" var="filmView">
                <div class="films">
                    <form action="${pageContext.request.contextPath}/controller?action=selectFilmView" method="post">
                        <div class="reg_text">
                            <img src="${filmView.cover}" class="img_news"/>
                        </div>
                        <div class="reg_text">
                                ${filmView.name}
                        </div>
                        <div class="reg_text">
                                ${filmView.premiere}
                        </div>
                        <div class="reg_text">
                                ${filmView.genre}
                        </div>
                        <input type="text" name="id" value="${filmView.id}" title="film name" hidden/>
                        <div class="reg_text">
                            <button type="submit">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="full.information" bundle="${lang}"/>
                            </button>
                        </div>
                    </form>
                </div>
            </c:forEach>
        </c:if>
    </div>
    <fl:footer/>
</div>
</body>
</html>
