<%@tag description="block news tamlate" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="filmBlock" type="com.epam.irasov.filmlibrary.entity.FilmBlock"--%>
<div class="films">
    <h2>
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="site.new.films" bundle="${lang}"/>
    </h2>
    <c:if test="${not empty filmBlock}">
        <p>block.news.name: ${filmBlock.name}</p>
        <c:forEach items="${filmBlock.films}" var="film">
            <div style="padding: 0 0 1% 5%">
                <p>news.name ${film.cover}</p>
                <p>news.date ${film.name}</p>
                <p>news.text ${film.premiere}</p>
            </div>
        </c:forEach>
    </c:if>
</div>