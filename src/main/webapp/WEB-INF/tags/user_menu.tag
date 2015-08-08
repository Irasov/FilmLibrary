<%@tag description="user menu tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<div class="nav_user">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="user.menu" bundle="${lang}"/>
    </div>
    <ul class="navigation_user">
        <li><a href="${pageContext.request.contextPath}/index.jsp">
            <span>
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="Edit.personal.data" bundle="${lang}"/>
            </span></a></li>
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="change.password" bundle="${lang}"/>
                </span></a></li>
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.reviews" bundle="${lang}"/>
            </span></a></li>
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.film" bundle="${lang}"/>
            </span></a></li>
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.honors" bundle="${lang}"/>
            </span></a></li>
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.actors" bundle="${lang}"/>
            </span></a></li>
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.users" bundle="${lang}"/>
            </span></a></li>
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.films" bundle="${lang}"/>
            </span></a></li>
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.user.reviews" bundle="${lang}"/>
            </span></a></li>
    </ul>
</div>