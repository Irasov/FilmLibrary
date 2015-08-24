<%@tag description="navigation tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="systemMember" type="com.epam.irasov.filmlibrary.entity.SystemMember"--%>
<div class="nav">
    <ul class="navigation">
        <li><a href="${pageContext.request.contextPath}/index.jsp">
            <span>
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="site.home" bundle="${lang}"/>
            </span></a></li>
        <li><a href="${pageContext.request.contextPath}/check-news.jsp">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.news" bundle="${lang}"/>
                </span></a></li>
        <li><a href="${pageContext.request.contextPath}/check-films.jsp">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.films" bundle="${lang}"/>
            </span></a></li>
        <li><a href="${pageContext.request.contextPath}/check-film-members.jsp">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.film.members" bundle="${lang}"/>
            </span></a></li>
        <c:if test="${empty systemMember}">
            <li><a href="${pageContext.request.contextPath}/registration.jsp">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.registration" bundle="${lang}"/>
            </span></a></li>
        </c:if>
        <c:if test="${empty systemMember}">
            <li><a href="${pageContext.request.contextPath}/login.jsp">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.login" bundle="${lang}"/>
            </span></a></li>
        </c:if>
        <li><a href="${pageContext.request.contextPath}/search.jsp">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.search" bundle="${lang}"/>
            </span></a></li>
        <c:if test="${not empty systemMember}">
            <li><a href="${pageContext.request.contextPath}/user.jsp">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.user" bundle="${lang}"/>
            </span></a></li>
        </c:if>
        <c:if test="${not empty systemMember}">
            <li><a href="${pageContext.request.contextPath}/controller?action=logout">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.logout" bundle="${lang}"/>
            </span></a></li>
        </c:if>
    </ul>
</div>
