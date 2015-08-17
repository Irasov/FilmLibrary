<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="filmMemberView" type="com.epam.irasov.filmlibrary.entity.FilmMember"--%>
<%--@elvariable id="filmsView" type="java.util.List"--%>
<%--@elvariable id="filmView" type="com.epam.irasov.filmlibrary.entity.Film"--%>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="site.film.members" bundle="${lang}"/>
    </title>
</head>
<body>
<div class="wrapper">
    <fl:header/>
    <fl:navigation/>
    <div class="container">
        <div class="films">
            <div class="reg_text">
                <img class="img" src="${filmMemberView.photo}"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.surname" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="surname" class="reginput" value="${filmMemberView.surname}" readonly
                       title="surname"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.name" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="name" class="reginput" value="${filmMemberView.name}" readonly title="name"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.patronymic" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="patronymic" class="reginput" value="${filmMemberView.patronymic}" readonly
                       title="patronymic"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.birthDate" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="birthdate" class="reginput" value="${filmMemberView.birthDate}" readonly
                       title="yyyy-mm-dd"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="filmMember.type" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="type" class="reginput" value="${filmMemberView.type.name}" readonly
                       title="type"/>
            </div>
            <c:if test="${not empty filmsView}">
                <div class="reg_text">
                    <div class="reg_text">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="member.films" bundle="${lang}"/>
                    </div>
                    <form action="${pageContext.request.contextPath}/controller?action=selectFilmView" method="post">
                        <select name="id" size="10" required title="select film">
                            <c:forEach items="${filmsView}" var="filmView">
                                <option value="${filmView.id}">${filmView.name}(${filmView.premiere})</option>
                                title="cover"/>
                            </c:forEach>
                        </select>
                        <div class="reg_text">
                            <button type="submit">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="full.information" bundle="${lang}"/>
                            </button>
                        </div>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
    <fl:footer/>
</div>
</body>
</html>

