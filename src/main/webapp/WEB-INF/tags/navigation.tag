<%@tag description="navigation tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<div class="nav">
    <ul class="navigation">
        <li><a href="/">
            <span>
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="site.home" bundle="${lang}"/>
            </span></a></li>
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.news" bundle="${lang}"/>
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
                <fmt:message key="site.actors" bundle="${lang}"/>
            </span></a></li>
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.other" bundle="${lang}"/>
            </span></a></li>
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.registration" bundle="${lang}"/>
            </span></a></li>
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.login" bundle="${lang}"/>
            </span></a></li>
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.search" bundle="${lang}"/>
            </span></a></li>
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.about" bundle="${lang}"/>
            </span></a></li>
    </ul>
</div>
