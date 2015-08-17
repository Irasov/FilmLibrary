<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="filmView" type="com.epam.irasov.filmlibrary.entity.Film"--%>
<%--@elvariable id="filmMembersView" type="java.util.List"--%>
<%--@elvariable id="filmMemberView" type="com.epam.irasov.filmlibrary.entity.FilmMember"--%>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="site.films" bundle="${lang}"/>
    </title>
</head>
<body>
<div class="wrapper">
    <fl:header/>
    <fl:navigation/>
    <div class="container">
        <div class="films">
            <div class="reg_text">
                <img class="img" src="${filmView.cover}"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.name" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="name" value="${filmView.name}" class="reginput" readonly
                       title="name"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.tagline" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <textarea name="tagLine" readonly class="tagline" title="tagLine">${filmView.tagLine}</textarea>
            </div>

            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.genre" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <textarea name="genre" readonly class="genre" title="genre">${filmView.genre}</textarea>
            </div>
            <c:if test="${not empty filmMembersView}">
                <div class="reg_text">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="film.member" bundle="${lang}"/>
                </div>
                <div class="reg_text">
                    <form action="${pageContext.request.contextPath}/controller?action=selectMemberView" method="post">
                        <div class="reg_text">
                            <select name="idMemberView" size="10" required title="select Member">
                                <c:forEach items="${filmMembersView}" var="filmMemberView">
                                    <option value="${filmMemberView.id}">${filmMemberView.surname}(${filmMemberView.type.name})</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="reg_text">
                            <button type="submit">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="full.information" bundle="${lang}"/>
                            </button>
                        </div>
                    </form>
                </div>
            </c:if>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.restriction.age" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <textarea name="age" class="reginput" readonly title="restriction age" rows="1"
                          cols="5">${filmView.ageRestriction}</textarea>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.duration" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <textarea name="duration" rows="1" cols="5" class="reginput" readonly
                          title="duration">${filmView.duration}</textarea>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="reg.description" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <textarea name="description" class="description" rows="10" cols="50" readonly
                          title="description">${filmView.description}</textarea>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.premiere" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="premiere" class="reginput" value="${filmView.premiere}" readonly
                       title="yyyy-mm-dd"/>
            </div>
        </div>
    </div>
    <fl:footer/>
</div>
</body>
</html>

