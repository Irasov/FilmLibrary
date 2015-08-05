<%@tag description="navigation tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<div class="login">
    <form action="${pageContext.request.contextPath}/controller?action=login" method="post">
        <div class="error">
           Введеные вами логи или пароль не верны!
        </div>
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.login" bundle="${lang}"/>
        </div>
       <%-- <c:if test="${not empty loginError}">
            <div class="error">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="${loginError}" bundle="${lang}"/>
            </div>
        </c:if>--%>
        <div class="reg_input">
            <input type="text" name="login" class="reginput" required title="login"/>
        </div>
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.password" bundle="${lang}"/>
        </div>
        <%--<c:if test="${not empty passwordError}">
            <div class="error">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="${passwordError}" bundle="${lang}"/>
            </div>
        </c:if>--%>
        <div class="reg_input">
            <input type="password" name="pas" class="reginput" required title="password"/>
        </div>
        <div class="reg_text">
            <button type="submit">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.enter" bundle="${lang}"/>
            </button>
        </div>
    </form>
    <a href="${pageContext.request.contextPath}/controller?action=register">
        <div class="no_login_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="site.not.registration" bundle="${lang}"/>
        </div>
    </a>
</div>
