<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="selectedAction" type="java.lang.String"--%>
<%--@elvariable id="messageBlock" type="java.lang.String"--%>
<%--@elvariable id="newsInBlock" type="java.util.List"--%>
<%--@elvariable id="newsBlock" type="com.epam.irasov.filmlibrary.entity.NewsBlock"--%>
<%--@elvariable id="messageErrorBlock" type="java.lang.String"--%>
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
        <div class="operation">
            <div class="selected">
                <div class="select">
                    <form action="${pageContext.request.contextPath}/controller?action=selectedOperationNewsBlock"
                          method="post">
                        <p><input type="radio" name="selected" value="1" checked title="add news"/>
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="block.add" bundle="${lang}"/></p>

                        <p><input type="radio" name="selected" value="2" title="remove"/>
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="block.delete" bundle="${lang}"/></p>
                        <button type="submit">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="site.select" bundle="${lang}"/>
                        </button>

                        <c:if test="${not empty messageBlock}">
                            <div class="complete">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="${messageBlock}" bundle="${lang}"/>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
            <c:if test="${selectedAction==1}">
                <c:if test="${empty newsInBlock}">
                    <div class="error">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="${messageErrorBlock}" bundle="${lang}"/>
                    </div>
                </c:if>
                <c:if test="${not empty newsInBlock}">
                    <fl:addNewsBlock/>
                </c:if>
            </c:if>
            <c:if test="${selectedAction==2}">
                <c:if test="${not empty newsBlock}">
                    <fl:removeNewsBlock/>
                </c:if>
                <c:if test="${empty newsInBlock}">
                    <div class="error">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="${messageErrorBlock}" bundle="${lang}"/>
                    </div>
                </c:if>
            </c:if>
        </div>
        <div class="user_menu"><fl:user_menu/></div>
    </div>
    <fl:footer/>
</div>
</body>
</html>