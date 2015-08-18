<%--@elvariable id="systemMember" type="com.epam.irasov.filmlibrary.entity.SystemMember"--%>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="messageEditPass" type="java.lang.String"--%>
<%--@elvariable id="passwordError" type="java.lang.String"--%>
<%--@elvariable id="passwordPreviousError" type="java.lang.String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="edit.password" bundle="${lang}"/>
    </title>
</head>
<body>
<div class="wrapper">
    <fl:header/>
    <fl:navigation/>
    <div class="container">
        <div class="edit_member">
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="edit.password" bundle="${lang}"/>
            </div>
            <c:if test="${not empty messageEditPass}">
                <div class="complete">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="${messageEditPass}" bundle="${lang}"/>
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/controller?action=editUserPassword" method="post">
                <input type="text" name="id" value="${systemMember.id}" hidden class="reginput" title="id"/>
                <input type="text" name="idType" value="${systemMember.type.id}" hidden class="reginput" title="id"/>
                <input type="text" name="nameType" value="${systemMember.type.name}" hidden class="reginput" title="id"/>
                <input type="text" name="photo" value="${systemMember.photo}" hidden class="reginput" title="photo"/>
                <input type="text" name="login" value="${systemMember.login}" hidden class="reginput" title="login"/>
                <input type="text" name="password" value="${systemMember.password}" hidden class="reginput" title="password"/>
                <input type="text" name="surname" class="reginput" value="${systemMember.surname}" hidden title="surname"/>
                <input type="text" name="name" class="reginput" value="${systemMember.name}" hidden title="name"/>
                <input type="text" name="patronymic" class="reginput" value="${systemMember.patronymic}" hidden title="patronymic"/>
                <input type="text" name="birthdate" class="reginput" value="${systemMember.birthDate}" hidden title="birth date">
                <input type="text" name="email"  value="${systemMember.email}" class="reginput" hidden title="email@example.com"/>
                <div class="reg_text">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="reg.previous.password" bundle="${lang}"/>
                </div>
                <c:if test="${not empty passwordPreviousError}">
                    <div class="error">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="${passwordPreviousError}" bundle="${lang}"/>
                    </div>
                </c:if>
                <div class="reg_input">
                    <input type="password" name="previousPass" class="reginput" required title="password"/>
                </div>
                <div class="reg_text">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="new.password" bundle="${lang}"/>
                </div>
                <c:if test="${not empty passwordError}">
                    <div class="error">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="${passwordError}" bundle="${lang}"/>
                    </div>
                </c:if>
                <div class="reg_input">
                    <input type="password" name="pass" class="reginput" required title="password"/>
                </div>
                <div class="reg_text">
                    <button type="submit">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="button.edit" bundle="${lang}"/>
                    </button>
                </div>
            </form>
        </div>
        <div class="user_menu"><fl:user_menu/></div>
    </div>
    <fl:footer/>
</div>
</body>
</html>
