<%@tag description="block news tamlate" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="filmBlock" type="com.epam.irasov.filmlibrary.entity.FilmBlock"--%>
<div class="films_block">
    <c:if test="${not empty filmBlock}">
        <div class="reg_text">
            <h3>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="${filmBlock.name}" bundle="${lang}"/>
            </h3>
            <c:forEach items="${filmBlock.films}" var="film">
                <form action="${pageContext.request.contextPath}/controller?action=selectFilmView" method="post">
                    <input name="id" hidden value="${film.id}" title=""/>
                    <div class="reg_text">
                            ${film.premiere}: ${film.name}
                        <br/>
                        <button type="submit">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="button.read" bundle="${lang}"/>
                        </button>
                    </div>
                </form>
            </c:forEach>
        </div>
    </c:if>
</div>