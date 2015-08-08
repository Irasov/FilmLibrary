<%@tag description="registration tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="loginError" type="java.lang.String"--%>
<%--@elvariable id="passwordError" type="java.lang.String"--%>
<%--@elvariable id="emailError" type="java.lang.String"--%>
<div class="reg">
    <form action="${pageContext.request.contextPath}/controller?action=register" method="post">
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.login" bundle="${lang}"/>
        </div>
        <c:if test="${not empty loginError}">
            <div class="error">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="${loginError}" bundle="${lang}"/>
            </div>
        </c:if>
        <div class="reg_input">
            <input type="text" name="login" class="reginput" required title="login"/>
        </div>
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.password" bundle="${lang}"/>
        </div>
        <c:if test="${not empty passwordError}">
            <div class="error">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="${passwordError}" bundle="${lang}"/>
            </div>
        </c:if>
        <div class="reg_input">
            <input type="password" name="pas" class="reginput" required title="password"/>
        </div>
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.surname" bundle="${lang}"/>
        </div>
        <div class="reg_input">
            <input type="text" name="surname" class="reginput" required title="surname"/>
        </div>
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.name" bundle="${lang}"/>
        </div>
        <div class="reg_input">
            <input type="text" name="name" class="reginput" required title="name"/>
        </div>
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.patronymic" bundle="${lang}"/>
        </div>
        <div class="reg_input">
            <input type="text" name="patronymic" class="reginput" required title="patronymic"/>
        </div>
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.email" bundle="${lang}"/>
        </div>
        <c:if test="${not empty emailError}">
            <div class="error">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="${emailError}" bundle="${lang}"/>
            </div>
        </c:if>
        <div class="reg_input">
            <input type="text" name="email" class="reginput" required title="email@example.com"/>
        </div>
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.birthdate" bundle="${lang}"/>
        </div>
        <div class="reg_input">
            <input type="text" name="birthdate" class="reginput" value="2015-08-20"
                   pattern="[0-9]{4}-[0-1]{1}[0-9]{1}-[0-9]{2}" required title="yyyy-mm-dd"/>
        </div>
        <div class="reg_text">
            <button type="submit">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.registration" bundle="${lang}"/>
            </button>
        </div>
    </form>
</div>
