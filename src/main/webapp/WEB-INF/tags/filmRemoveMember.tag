<%@tag description="add film tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="fileName" type="java.lang.String"--%>
<%--@elvariable id="filmMembersRemove" type="java.util.List"--%>
<%--@elvariable id="filmMember" type="com.epam.irasov.filmlibrary.entity.FilmMember"--%>
<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="films" type="java.util.List"--%>
<%--@elvariable id="film" type="com.epam.irasov.filmlibrary.entity.Film"--%>
<%--@elvariable id="errorMembers" type="java.lang.String"--%>
<%--@elvariable id="idFilmRemove" type="java.lang.Long"--%>
<div class="remove_film_film_member">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="film.remove.member" bundle="${lang}"/>
    </div>
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="select.film" bundle="${lang}"/>
    </div>
    <form action="${pageContext.request.contextPath}/controller?action=selectFilmFilmMember" method="post">
        <div class="reg_text">
            <select name="idFilm" size="10" required title="select film">
                <c:forEach items="${films}" var="film">
                    <option value="${film.id}">${film.name}(${film.premiere})</option>
                </c:forEach>
            </select>
        </div>
        <div class="reg_text">
            <button type="submit">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.select" bundle="${lang}"/>
            </button>
        </div>
    </form>
    <c:if test="${not empty errorMembers}">
        <div class="error">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="${errorMembers}" bundle="${lang}"/>
        </div>
    </c:if>

    <c:if test="${not empty filmMembersRemove}">
        <form action="${pageContext.request.contextPath}/controller?action=removeFilmFilmMember" method="post">
            <div class="reg_text">
                <div>
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="members.select" bundle="${lang}"/>
                </div>
                <select name="idMember" size="10" required multiple title="select Member">
                    <c:forEach items="${filmMembersRemove}" var="filmMember">
                        <option value="${filmMember.id}">${filmMember.surname}(${filmMember.birthDate})</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="idFilm" value="${idFilmRemove}">
            </div>
            <div class="reg_text">
                <button type="submit">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="button.remove" bundle="${lang}"/>
                </button>
            </div>
        </form>
    </c:if>
</div>