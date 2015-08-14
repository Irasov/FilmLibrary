<%@tag description="add film tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="fileName" type="java.lang.String"--%>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="selectedAction" type="java.lang.String"--%>
<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="fileError" type="java.lang.String"--%>
<%--@elvariable id="types" type="java.util.List"--%>
<div class="add_member">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="add.member" bundle="${lang}"/>
    </div>
    <c:if test="${empty fileName}">
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="member.photo.select" bundle="${lang}"/>
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
        <form action="${pageContext.request.contextPath}/controller?action=addPhoto" method="post"
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
    <form action="${pageContext.request.contextPath}/controller?action=addMember" method="post">
        <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.type" bundle="${lang}"/>
        </div>
        <div class="reg_text">
            <select name="idType" size="5" required title="select type">
                <c:forEach items="${types}" var="type">
                    <option value="${type.id}">${type.name}</option>
                </c:forEach>
            </select>
        </div>
        <form action="${pageContext.request.contextPath}/controller?action=addMember" method="post">
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="reg.surname" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="surname" class="reginput" required title="surname"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="reg.name" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="name" class="reginput" required title="name"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="reg.patronymic" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="patronymic" class="reginput" required title="patronymic"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="reg.birthdate" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="birthdate" class="reginput" value="2015-08-20"
                       pattern="[0-9]{4}-[0-1]{1}[0-9]{1}-[0-9]{2}" required title="yyyy-mm-dd"/>
            </div>
            <input type="hidden" name="fileName" value="${fileName}">
            <div class="reg_text">
                <button type="submit">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="add.member" bundle="${lang}"/>
                </button>
            </div>
        </form>
    </c:if>
</div>