<%@tag description="remove films" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="filmsBlock" type="com.epam.irasov.filmlibrary.entity.FilmBlock"--%>
<%--@elvariable id="filmsInBlock" type="java.util.List"--%>
<div class="add_member_film">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="block.film.delete" bundle="${lang}"/>
    </div>
    <form action="${pageContext.request.contextPath}/controller?action=blockRemoveFilm" method="post">
        <div class="reg_text">
            <div>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="select.film.block.remove" bundle="${lang}"/>
            </div>
            <select name="idFilm" size="10" required multiple title="select News">
                <c:forEach items="${filmsBlock.films}" var="film">
                    <option value="${film.id}">${film.name}(${film.premiere})</option>
                </c:forEach>
            </select>
        </div>
        <div class="reg_text">
            <button type="submit">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="button.remove" bundle="${lang}"/>
            </button>
        </div>
    </form>
</div>