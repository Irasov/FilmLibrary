<%@tag description="user menu tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="systemMember" type="com.epam.irasov.filmlibrary.entity.SystemMember"--%>
<div class="nav_user">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="user.menu" bundle="${lang}"/>
    </div>
    <ul class="navigation_user">
        <li><a href="${pageContext.request.contextPath}/user.jsp">
            <span>
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="my.information" bundle="${lang}"/>
            </span></a></li>
        <li><a href="${pageContext.request.contextPath}/edit-personal-data.jsp">
            <span>
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="edit.personal.data" bundle="${lang}"/>
            </span></a></li>
        <li><a href="${pageContext.request.contextPath}/change-password.jsp">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="change.password" bundle="${lang}"/>
                </span></a></li>
        <li><a href="${pageContext.request.contextPath}/my-reviews.jsp">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.reviews" bundle="${lang}"/>
            </span></a></li>
        <li><a href="${pageContext.request.contextPath}/my-film.jsp">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.film" bundle="${lang}"/>
            </span></a></li>
        <c:if test="${systemMember.type.id==1}">
            <li><a href="${pageContext.request.contextPath}/operation-with-movies.jsp">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.films" bundle="${lang}"/>
            </span></a></li>
        </c:if>
        <c:if test="${systemMember.type.id==1}">
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.members.film" bundle="${lang}"/>
            </span></a></li>
        </c:if>
        <c:if test="${systemMember.type.id==1}">
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.users" bundle="${lang}"/>
            </span></a></li>
        </c:if>
        <c:if test="${systemMember.type.id==1}">
        <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="user.user.reviews" bundle="${lang}"/>
            </span></a></li>
        </c:if>
        <c:if test="${systemMember.type.id==1}">
            <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.news" bundle="${lang}"/>
            </span></a></li>
        </c:if>
        <c:if test="${systemMember.type.id==1}">
            <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.block.news" bundle="${lang}"/>
            </span></a></li>
        </c:if>
        <c:if test="${systemMember.type.id==1}">
            <li><a href="/">
            <span>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="site.block.film" bundle="${lang}"/>
            </span></a></li>
        </c:if>
    </ul>
</div>