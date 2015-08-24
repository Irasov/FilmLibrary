<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="filmsView" type="java.util.List"--%>
<%--@elvariable id="filmView" type="com.epam.irasov.filmlibrary.entity.Film"--%>
<%--@elvariable id="var" type="java.lang.Integer"--%>

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
        <div class="container_film">
            <c:if test="${not empty filmsView}">
                <c:forEach items="${filmsView}" var="filmView">
                    <div class="film">
                        <form action="${pageContext.request.contextPath}/controller?action=selectFilmView"
                              method="post">
                            <div class="reg_text">
                                <img src="${filmView.cover}" class="img_news"/>
                            </div>
                            <div class="reg_text">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="film.name" bundle="${lang}"/> ${filmView.name}
                            </div>
                            <div class="reg_text">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="film.premiere" bundle="${lang}"/>: ${filmView.premiere}
                            </div>
                            <div class="reg_text">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="film.genre" bundle="${lang}"/>: ${filmView.genre}
                            </div>
                            <input type="text" name="id" value="${filmView.id}" title="film name" hidden/>
                            <div class="reg_text">
                                <button type="submit">
                                    <fmt:setLocale value="${locale}"/>
                                    <fmt:setBundle basename="i18n" var="lang"/>
                                    <fmt:message key="full.information" bundle="${lang}"/>
                                </button>
                            </div>
                            <input type="text" name="end" value="${var=var+1}" title="film name" hidden/>
                        </form>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
    <c:if test="${empty filmsView}">
        <fl:footer/>
    </c:if>
</div>
</body>
</html>
