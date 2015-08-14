<%@tag description="add film tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="films" type="java.util.List"--%>
<%--@elvariable id="film" type="com.epam.irasov.filmlibrary.entity.Film"--%>
<div class="removeFilm">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="film.remove" bundle="${lang}"/>
    </div>
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="select.film.remove" bundle="${lang}"/>
    </div>
    <form action="${pageContext.request.contextPath}/controller?action=removeFilm" method="post">
        <div class="reg_text">
            <select name="idFilm" size="10" required title="select film">
                <c:forEach items="${films}" var="film">
                    <option value="${film.id}">${film.name}(${film.premiere})</option>
                    <input type="text" name="cover" value="${film.cover}" hidden class="reginput"
                           title="cover"/>
                </c:forEach>
            </select>
        </div>
        <div class="reg_text">
            <button type="submit">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.remove" bundle="${lang}"/>
            </button>
        </div>
    </form>
</div>