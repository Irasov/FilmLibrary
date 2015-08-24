<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="messageSearchFilm" type="java.lang.String"--%>
<%--@elvariable id="filmSearch" type="com.epam.irasov.filmlibrary.entity.Film"--%>
<%--@elvariable id="messageSearchMember" type="java.lang.String"--%>
<%--@elvariable id="memberSearch" type="com.epam.irasov.filmlibrary.entity.FilmMember"--%>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="site.search" bundle="${lang}"/>
    </title>
</head>
<body>
<div class="wrapper">
    <fl:header/>
    <fl:navigation/>
    <div class="container">
        <div class="content">
            <form action="${pageContext.request.contextPath}/controller?action=searchFilm" method="post">
                <div class="reg_text">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="film.search.name" bundle="${lang}"/>
                </div>
                <div class="reg_text">
                    <input type="text" name="name" class="reginput" required title="name"/>
                </div>
                <div class="reg_text">
                    <button type="submit">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="button.search" bundle="${lang}"/>
                    </button>
                </div>
            </form>

            <form action="${pageContext.request.contextPath}/controller?action=searchMember" method="post">
                <div class="reg_text">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="member.search.surname" bundle="${lang}"/>
                </div>
                <div class="reg_text">
                    <input type="text" name="surName" class="reginput" required title="surname"/>
                </div>
                <div class="reg_text">
                    <button type="submit">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="button.search" bundle="${lang}"/>
                    </button>
                </div>
            </form>

            <c:if test="${not empty messageSearchFilm}">
                <div class="error">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="${messageSearchFilm}" bundle="${lang}"/>
                </div>
            </c:if>

            <c:if test="${not empty messageSearchMember}">
                <div class="error">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="${messageSearchMember}" bundle="${lang}"/>
                </div>
            </c:if>
            <c:if test="${not empty memberSearch}">
                <form action="${pageContext.request.contextPath}/controller?action=selectMemberView" method="post">
                    <div class="reg_text">
                        <img class="img_news" src="${memberSearch.photo}"/>
                    </div>
                    <div class="reg_text">
                            ${memberSearch.surname} ${memberSearch.patronymic} ${memberSearch.name}
                    </div>
                    <div class="reg_text">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="user.birthDate" bundle="${lang}"/>: ${memberSearch.birthDate}
                    </div>
                    <input type="text" name="idMemberView" value="${memberSearch.id}" title="filmMember name" hidden/>

                    <div class="reg_text">
                        <button type="submit">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="full.information" bundle="${lang}"/>
                        </button>
                    </div>
                </form>
            </c:if>
            <c:if test="${not empty filmSearch}">
                <form action="${pageContext.request.contextPath}/controller?action=selectFilmView"
                      method="post">
                    <div class="reg_text">
                        <img src="${filmSearch.cover}" class="img_news"/>
                    </div>
                    <div class="reg_text">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="film.name" bundle="${lang}"/> ${filmSearch.name}
                    </div>
                    <div class="reg_text">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="film.premiere" bundle="${lang}"/>: ${filmSearch.premiere}
                    </div>
                    <div class="reg_text">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="film.genre" bundle="${lang}"/>: ${filmSearch.genre}
                    </div>
                    <input type="text" name="id" value="${filmSearch.id}" title="film name" hidden/>

                    <div class="reg_text">
                        <button type="submit">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="full.information" bundle="${lang}"/>
                        </button>
                    </div>
                </form>
            </c:if>
        </div>
    </div>
    <fl:footer/>
</div>
</body>
</html>
