<%@tag description="add film tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="fileName" type="java.lang.String"--%>
<%--@elvariable id="loginError" type="java.lang.String"--%>
<%--@elvariable id="passwordError" type="java.lang.String"--%>
<%--@elvariable id="emailError" type="java.lang.String"--%>
<%--@elvariable id="selectedAction" type="java.lang.String"--%>
<%--@elvariable id="tagLineError" type="java.lang.String"--%>
<%--@elvariable id="restrictionError" type="java.lang.String"--%>
<%--@elvariable id="descriptionError" type="java.lang.String"--%>
<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="films" type="java.util.List"--%>
<%--@elvariable id="film" type="com.epam.irasov.filmlibrary.entity.Film"--%>
<%--@elvariable id="fileError" type="java.lang.String"--%>
<div class="edit_film">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="edit.film" bundle="${lang}"/>
    </div>
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="select.film" bundle="${lang}"/>
    </div>
    <form action="${pageContext.request.contextPath}/controller?action=selectFilm" method="post">
        <div class="reg_text">
            <select name="idFilm" size="10" required title="select film">
                <c:forEach items="${films}" var="film">
                    <option value="${film.id}">${film.name}(${film.premiere})</option>
                </c:forEach>
            </select>
        </div>
        <div class="reg_text">
            <button type="submit">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.select" bundle="${lang}"/>
            </button>
        </div>
    </form>
    <c:if test="${not empty film}">
        <c:if test="${not empty fileError}">
            <div class="error">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="${fileError}" bundle="${lang}"/>
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/controller?action=editImage" method="post"
              enctype="multipart/form-data">
            <div class="reg_input">
                <input type="file" class="reginput" name="file" accept="image/jpeg,image/png"/>
            </div>
            <input type="text" name="strar" value="${film.cover}" hidden class="reginput"
                   title="cover"/>

            <div class="reg_input">
                <button type="submit">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="file.upload" bundle="${lang}"/>
                </button>
            </div>
        </form>
        <c:if test="${not empty fileName}">
            <c:redirect
                    url="/controller?action=editFilm&name=${film.name}&id=${film.id}&tagLine=${film.tagLine}&age=${film.ageRestriction}&duration=${film.duration}&description=${film.description}&description=${film.description}&premiere=${film.premiere}&rating=${film.rating.id}&cover=img/site/${fileName}"/>
        </c:if>
        <form action="${pageContext.request.contextPath}/controller?action=editFilm" method="post">
            <input type="text" name="id" value="${film.id}" hidden class="reginput" title="id"/>
            <input type="text" name="rating" value="${film.rating.id}" hidden class="reginput"
                   title="rating"/>
            <input type="text" name="cover" value="${film.cover}" hidden class="reginput"
                   title="cover"/>

            <div class="reg_text">
                <img class="img" src="${film.cover}"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.name" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="name" value="${film.name}" class="reginput" required
                       title="name"/>
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
                                <textarea name="tagLine" required class="tagline"
                                          title="tagLine">${film.tagLine}</textarea>
            </div>

            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.genre" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <textarea name="genre" required class="genre" title="genre">${film.genre}</textarea>
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
                                      cols="5">${film.ageRestriction}</textarea>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.duration" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                            <textarea name="duration" rows="1" cols="5" class="reginput" required
                                      title="duration">${film.duration}</textarea>
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
                                      title="description">${film.description}</textarea>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.premiere" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="premiere" class="reginput" value="${film.premiere}"
                       pattern="[0-9]{4}-[0-1]{1}[0-9]{1}-[0-9]{2}" required title="yyyy-mm-dd"/>
            </div>
            <div class="reg_text">
                <button type="submit">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="film.edit" bundle="${lang}"/>
                </button>
            </div>
        </form>
    </c:if>
</div>