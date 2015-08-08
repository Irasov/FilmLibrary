<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="systemMember" type="com.epam.irasov.filmlibrary.entity.SystemMember"--%>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>
        personal.area
    </title>
</head>
<body>
<div class="wrapper">
    <fl:header/>
    <fl:navigation/>
    <div class="container">
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="personal.data" bundle="${lang}"/>
        </div>
        <div class="avatar">
            <img class="img" src="${systemMember.photo}" title="avatar"/>
        </div>
        <div class="data">
            <p>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.login" bundle="${lang}"/>
                ${systemMember.login}
            </p>

            <p>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.surname" bundle="${lang}"/>
                ${systemMember.surname}
            </p>

            <p>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.name" bundle="${lang}"/>
                ${systemMember.name}
            </p>

            <p>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.patronymic" bundle="${lang}"/>
                ${systemMember.patronymic}
            </p>

            <p>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.birthDate" bundle="${lang}"/>
                ${systemMember.birthDate}
            </p>

            <p>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.email" bundle="${lang}"/>
                ${systemMember.email}
            </p>
        </div>
        <div class="user_menu"><fl:user_menu/></div>
    </div>
    <fl:footer/>
</div>
</body>
</html>
