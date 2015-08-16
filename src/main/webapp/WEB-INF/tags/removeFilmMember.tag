<%@tag description="add film tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="filmMembers" type="java.util.List"--%>
<%--@elvariable id="filmMember" type="com.epam.irasov.filmlibrary.entity.FilmMember"--%>
<div class="removeFilm">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="remove.member" bundle="${lang}"/>
    </div>
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="select.member" bundle="${lang}"/>
    </div>
    <form action="${pageContext.request.contextPath}/controller?action=removeFilmMember" method="post">
        <div class="reg_text">
            <select name="idFilmMember" size="10" required title="select film">
                <c:forEach items="${filmMembers}" var="filmMember">
                    <option value="${filmMember.id}+${filmMember.photo}">${filmMember.surname}(${filmMember.birthDate})</option>
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