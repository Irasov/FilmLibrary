<%@tag description="add film tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="news" type="java.util.List"--%>
<%--@elvariable id="item" type="com.epam.irasov.filmlibrary.entity.News"--%>
<div class="removeFilm">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="remove.news" bundle="${lang}"/>
    </div>
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="select.news" bundle="${lang}"/>
    </div>
    <form action="${pageContext.request.contextPath}/controller?action=removeNews" method="post">
        <div class="reg_text">
            <select name="idNews" size="10" required title="select film">
                <c:forEach items="${news}" var="item">
                    <option value="${item.id}+${item.image}">${item.name}(${item.date})</option>
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