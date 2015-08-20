<%@tag description="add films in list" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="filmsBlock" type="com.epam.irasov.filmlibrary.entity.FilmBlock"--%>
<%--@elvariable id="filmsInBlock" type="java.util.List"--%>
<div class="add_member_film">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="block.film.add" bundle="${lang}"/>
    </div>
    <form action="${pageContext.request.contextPath}/controller?action=blockAddFilm" method="post">
        <div class="reg_text">
            <div>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="select.film.block" bundle="${lang}"/>
            </div>
            <select name="idFilm" size="10" required title="select News">
                <c:forEach items="${filmsInBlock}" var="film">
                    <option value="${film.id}">${film.name}(${film.premiere})</option>
                </c:forEach>
            </select>
        </div>
        <div class="reg_text">
            <button type="submit">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="button.add" bundle="${lang}"/>
            </button>
        </div>
    </form>
</div>