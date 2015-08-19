<%@tag description="add film tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="fileName" type="java.lang.String"--%>
<%--@elvariable id="selectedAction" type="java.lang.String"--%>
<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="news" type="java.util.List"--%>
<%--@elvariable id="item" type="com.epam.irasov.filmlibrary.entity.News"--%>
<%--@elvariable id="fileError" type="java.lang.String"--%>
<%--@elvariable id="textError" type="java.lang.String"--%>
<div class="edit_film">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="edit.news" bundle="${lang}"/>
    </div>
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="select.news" bundle="${lang}"/>
    </div>
    <form action="${pageContext.request.contextPath}/controller?action=selectNews" method="post">
        <div class="reg_text">
            <select name="idNews" size="10" required title="select news">
                <c:forEach items="${news}" var="item">
                    <option value="${item.id}">${item.name}(${item.date})</option>
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
    <c:if test="${not empty item}">
        <c:if test="${not empty fileError}">
            <div class="error">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="${fileError}" bundle="${lang}"/>
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/controller?action=editImage&page=operation-with-news" method="post"
              enctype="multipart/form-data">
            <div class="reg_input">
                <input type="file" class="reginput" name="file" accept="image/jpeg,image/png"/>
            </div>
            <input type="text" name="strar" value="${item.image}" hidden class="reginput"
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
                    url="/controller?action=editNews&title=${item.name}&id=${item.id}&text=${item.text}&date=${item.date}&image=img/site/${fileName}"/>
        </c:if>
        <form action="${pageContext.request.contextPath}/controller?action=editNews" method="post">
            <input type="text" name="id" value="${item.id}" hidden class="reginput" title="id"/>
            <input type="text" name="image" value="${item.image}" hidden class="reginput"
                   title="cover"/>

            <div class="reg_text">
                <img class="img" src="${item.image}"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="news.title" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="title" value="${item.name}" class="reginput" required
                       title="name"/>
            </div>
            <c:if test="${not empty textError}">
                <div class="error">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="${textError}" bundle="${lang}"/>
                </div>
            </c:if>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="news.text" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <textarea name="text" class="description" rows="10" cols="70" required title="text">${item.text}</textarea>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="news.date" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="date" class="reginput" value="${item.date}"
                       pattern="[0-9]{4}-[0-1]{1}[0-9]{1}-[0-9]{2}" required title="yyyy-mm-dd"/>
            </div>
            <div class="reg_text">
                <button type="submit">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="button.edit" bundle="${lang}"/>
                </button>
            </div>
        </form>
    </c:if>
</div>