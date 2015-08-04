<%@tag description="navigation tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<div class="reg">
    <form action="${pageContext.request.contextPath}/controller?action=register" method="post">
        <div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="reg.text" bundle="${lang}"/>
            </div>
        </div>
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.login" bundle="${lang}"/>
        </div>
        <div class="reg_input">
            <input type="text" name="login" class="reginput" required title="login"/>
        </div>
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.password" bundle="${lang}"/>
        </div>
        <div class="reg_input">
            <input type="password" name="pas" class="reginput" required title="password"/>
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
            <fmt:message key="reg.surname" bundle="${lang}"/>
        </div>
        <div class="reg_input">
            <input type="text" name="surname" class="reginput" required title="surname"/>
        </div>
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.email" bundle="${lang}"/>
        </div>
        <div class="reg_input">
            <input type="text" name="email" class="reginput" required title="email"/>
        </div>
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.birthdate" bundle="${lang}"/>
        </div>
        <div class="reg_input">
            <input type="text" name="birthdate" class="reginput" required title="birthdate"/>
        </div>
        <div class="reg_text">
            <button type="submit">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.registration" bundle="${lang}"/>
            </button>
        </div>
    </form>
    <a href="${pageContext.request.contextPath}/controller?action=register">
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="site.not.registration" bundle="${lang}"/>
        </div>
    </a>
</div>
