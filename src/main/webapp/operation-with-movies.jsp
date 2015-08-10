<%--@elvariable id="fileName" type="java.lang.String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="loginError" type="java.lang.String"--%>
<%--@elvariable id="passwordError" type="java.lang.String"--%>
<%--@elvariable id="emailError" type="java.lang.String"--%>
<%--@elvariable id="selectedAction" type="java.lang.String"--%>
<%--@elvariable id="tagLineError" type="java.lang.String"--%>
<%--@elvariable id="restrictionError" type="java.lang.String"--%>
<%--@elvariable id="descriptionError" type="java.lang.String"--%>
<%--@elvariable id="message" type="java.lang.String"--%>
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
        <div class="selected">
            <div class="select">
                <form action="${pageContext.request.contextPath}/controller?action=selected" method="post">
                    <p><input type="radio" name="selected" value="1" checked title="add film"/>
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="add.film" bundle="${lang}"/></p>

                    <p><input type="radio" name="selected" value="2" title="remove"/>
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="edit.film" bundle="${lang}"/></p>

                    <p><input type="radio" name="selected" value="3" title="count"/>
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="film.add.member" bundle="${lang}"/></p>

                    <p><input type="radio" name="selected" value="4" title="count"/>
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="film.add.genre" bundle="${lang}"/></p>

                    <p><input type="radio" name="selected" value="5" title="count"/>
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="film.remove" bundle="${lang}"/></p>
                    <button type="submit">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="site.select" bundle="${lang}"/>
                    </button>
                    <c:if test="${not empty message}">
                        <div class="complete">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="${message}" bundle="${lang}"/>
                        </div>
                    </c:if>
                </form>
            </div>
        </div>

        <c:if test="${selectedAction==1}">
            <div class="add_film">
                <div class="reg_text">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="add.film" bundle="${lang}"/>
                </div>
                <c:if test="${empty fileName}">
                    <div class="reg_text">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="film.image.select" bundle="${lang}"/>
                    </div>
                </c:if>
                <c:if test="${not empty fileName}">
                    <img src="img/${fileName}" class="img"/>
                </c:if>
                <c:if test="${empty fileName}">
                    <form action="${pageContext.request.contextPath}/controller?action=addImage" method="post"
                          enctype="multipart/form-data">
                        <div class="reg_input">
                        </div>
                        <div class="reg_input">
                            <input type="file" class="reginput" name="file" accept="image/jpeg,image/png"/>
                        </div>
                        <div class="reg_input">
                            <button type="submit">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="file.upload" bundle="${lang}"/>
                            </button>
                        </div>
                    </form>
                </c:if>
                <c:if test="${not empty fileName}">
                    <form action="${pageContext.request.contextPath}/controller?action=addFilm" method="post">
                        <div class="reg_text">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="film.name" bundle="${lang}"/>
                        </div>
                        <div class="reg_input">
                            <input type="text" name="name" class="reginput" required title="name"/>
                        </div>
                        <div class="reg_text">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="film.tagline" bundle="${lang}"/>
                        </div>
                        <c:if test="${not empty tagLineError}">
                            <div class="error">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="${tagLineError}" bundle="${lang}"/>
                            </div>
                        </c:if>
                        <div class="reg_input">
                            <textarea name="tagLine" required class="tagline" title="tagLine"></textarea>
                        </div>
                        <div class="reg_text">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="film.restriction.age" bundle="${lang}"/>
                        </div>
                        <c:if test="${not empty restrictionError}">
                            <div class="error">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="${restrictionError}" bundle="${lang}"/>
                            </div>
                        </c:if>
                        <div class="reg_input">
                            <textarea name="age" class="reginput" required title="restriction age" rows="1"
                                      cols="5">0</textarea>
                        </div>
                        <div class="reg_text">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="film.duration" bundle="${lang}"/>
                        </div>
                        <div class="reg_input">
                            <textarea name="duration" rows="1" cols="5" class="reginput" required
                                      title="duration">0</textarea>
                        </div>
                        <div class="reg_text">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="reg.description" bundle="${lang}"/>
                        </div>
                        <c:if test="${not empty descriptionError}">
                            <div class="error">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="${descriptionError}" bundle="${lang}"/>
                            </div>
                        </c:if>
                        <div class="reg_input">
                            <textarea name="description" class="description" rows="10" cols="50" required
                                      title="description"></textarea>
                        </div>
                        <div class="reg_text">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="film.premiere" bundle="${lang}"/>
                        </div>
                        <div class="reg_input">
                            <input type="text" name="premiere" class="reginput" value="2015-08-20"
                                   pattern="[0-9]{4}-[0-1]{1}[0-9]{1}-[0-9]{2}" required title="yyyy-mm-dd"/>
                        </div>
                        <input type="hidden" name="fileName" value="${fileName}">

                        <div class="reg_text">
                            <button type="submit">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="add.film" bundle="${lang}"/>
                            </button>
                        </div>
                    </form>
                </c:if>
            </div>
        </c:if>
        <c:if test="${selectedAction==2}">
            <div class="edit_film">
                edit
            </div>
        </c:if>

        <div class="user_menu"><fl:user_menu/></div>
    </div>
    <fl:footer/>
</div>
</body>
</html>