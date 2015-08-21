<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="filmView" type="com.epam.irasov.filmlibrary.entity.Film"--%>
<%--@elvariable id="filmMembersViewIn" type="java.util.List"--%>
<%--@elvariable id="filmMemberView" type="com.epam.irasov.filmlibrary.entity.FilmMember"--%>
<%--@elvariable id="systemMember" type="com.epam.irasov.filmlibrary.entity.SystemMember"--%>
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
                <fmt:message key="film.name" bundle="${lang}"/> ${filmView.name}
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.tagline" bundle="${lang}"/>: ${filmView.tagLine}
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.premiere" bundle="${lang}"/>: ${filmView.premiere}
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.genre" bundle="${lang}"/>: ${filmView.genre}
            </div>
            <c:if test="${not empty filmMembersViewIn}">
                <div class="reg_text">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="film.member" bundle="${lang}"/>
                </div>
                <div class="reg_text">
                    <form action="${pageContext.request.contextPath}/controller?action=selectMemberView" method="post">
                        <div class="reg_text">
                            <select name="idMemberView" size="10" required title="select Member">
                                <c:forEach items="${filmMembersViewIn}" var="filmMemberView">
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
                <fmt:message key="film.restriction.age" bundle="${lang}"/>: ${filmView.ageRestriction}+
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.duration" bundle="${lang}"/>: ${filmView.duration}
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="reg.description" bundle="${lang}"/>:
                <br/>

                <p>${filmView.description}</p>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.rating" bundle="${lang}"/> ${filmView.rating.votes}
                <c:if test="${not empty systemMember}">
                    <form action="${pageContext.request.contextPath}/controller?action=incVotes" method="post">
                        <div class="reg_text">
                            <input type="text" name="idRating" value="${filmView.rating.id}" hidden class="reginput"
                                   title="id"/>
                            <input type="text" name="id" value="${filmView.id}" hidden class="reginput" title="id"/>
                            <input type="text" name="votes" value="${filmView.rating.votes}" hidden class="reginput"
                                   title="id"/>
                            <button type="submit">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="film.votes" bundle="${lang}"/>
                            </button>
                        </div>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
    <fl:footer/>
</div>
</body>
</html>

