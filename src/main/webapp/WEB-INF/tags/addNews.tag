<%@tag description="add film tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="fileName" type="java.lang.String"--%>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="selectedAction" type="java.lang.String"--%>
<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="fileError" type="java.lang.String"--%>
<%--@elvariable id="textError" type="java.lang.String"--%>
<div class="add_member">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="add.news" bundle="${lang}"/>
    </div>
    <c:if test="${empty fileName}">
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="news.image.select" bundle="${lang}"/>
        </div>
        <c:if test="${not empty fileError}">
            <div class="error">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="${fileError}" bundle="${lang}"/>
            </div>
        </c:if>
    </c:if>
    <c:if test="${not empty fileName}">
        <div class="reg_text">
            <img src="img/site/${fileName}" class="img"/>
        </div>
    </c:if>
    <c:if test="${empty fileName}">
        <form action="${pageContext.request.contextPath}/controller?action=addImage&page=operation-with-news" method="post"
              enctype="multipart/form-data">
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
        <form action="${pageContext.request.contextPath}/controller?action=addNews" method="post">
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="news.title" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="title" class="reginput" required title="title"/>
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
                <textarea name="text" class="description" rows="10" cols="70" required title="text"></textarea>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="news.date" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="date" class="reginput" value="2015-08-20"
                       pattern="[0-9]{4}-[0-1]{1}[0-9]{1}-[0-9]{2}" required title="yyyy-mm-dd"/>
            </div>
            <input type="hidden" name="fileName" value="${fileName}">

            <div class="reg_text">
                <button type="submit">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="add.news" bundle="${lang}"/>
                </button>
            </div>
        </form>
    </c:if>
</div>