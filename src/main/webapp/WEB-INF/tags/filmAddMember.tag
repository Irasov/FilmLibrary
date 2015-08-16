<%@tag description="add film tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="films" type="java.util.List"--%>
<%--@elvariable id="film" type="com.epam.irasov.filmlibrary.entity.Film"--%>
<%--@elvariable id="filmMembers" type="java.util.List"--%>
<%--@elvariable id="filmMember" type="com.epam.irasov.filmlibrary.entity.FilmMember"--%>
<div class="add_member_film">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="film.add.member" bundle="${lang}"/>
    </div>
    <form action="${pageContext.request.contextPath}/controller?action=memberAddFilm" method="post">
        <div class="reg_text_select_film">
            <div>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.select" bundle="${lang}"/>
            </div>
            <select name="idFilm" size="10" required title="select film">
                <c:forEach items="${films}" var="film">
                    <option value="${film.id}">${film.name}(${film.premiere})</option>
                </c:forEach>
            </select>
        </div>
        <div class="reg_text_select_film">
            <div>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="members.select" bundle="${lang}"/>
            </div>
            <select name="idMember" size="10" required multiple title="select Member">
                <c:forEach items="${filmMembers}" var="filmMember">
                    <option value="${filmMember.id}">${filmMember.surname}(${filmMember.birthDate})</option>
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