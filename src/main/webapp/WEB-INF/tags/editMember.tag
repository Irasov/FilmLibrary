<%@tag description="add film tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="filmMembers" type="java.util.List"--%>
<%--@elvariable id="filmMember" type="com.epam.irasov.filmlibrary.entity.FilmMember"--%>
<%--@elvariable id="fileName" type="java.lang.String"--%>
<%--@elvariable id="selectedAction" type="java.lang.String"--%>
<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="fileError" type="java.lang.String"--%>
<div class="edit_member">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="edit.member" bundle="${lang}"/>
    </div>
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="select.member" bundle="${lang}"/>
    </div>
    <form action="${pageContext.request.contextPath}/controller?action=selectMember" method="post">
        <div class="reg_text">
            <select name="idMember" size="10" required title="select Member">
                <c:forEach items="${filmMembers}" var="filmMember">
                    <option value="${filmMember.id}">${filmMember.name}(${filmMember.birthDate})</option>
                </c:forEach>
            </select>
        </div>
        <div class="reg_text">
            <button type="submit">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="button.select" bundle="${lang}"/>
            </button>
        </div>
    </form>

    <c:if test="${not empty filmMember}">
        <c:if test="${not empty fileError}">
            <div class="error">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="${fileError}" bundle="${lang}"/>
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/controller?action=editPhotoMember" method="post"
              enctype="multipart/form-data">
            <div class="reg_input">
                <input type="file" class="reginput" name="file" accept="image/jpeg,image/png"/>
            </div>
            <input type="text" name="strar" value="${filmMember.photo}" hidden class="reginput"
                   title="photo"/>

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
                    url="/controller?action=editFilmMember&name=${filmMember.name}&id=${filmMember.id}&surname=${filmMember.surname}&patronymic=${filmMember.patronymic}&birthdate=${filmMember.birthDate}&photo=img/site/${fileName}&idType=${filmMember.type.id}&nameType=${filmMember.type.name}"/>
        </c:if>
        <form action="${pageContext.request.contextPath}/controller?action=editFilmMember" method="post">
            <input type="text" name="id" value="${filmMember.id}" hidden class="reginput" title="id"/>
            <input type="text" name="idType" value="${filmMember.type.id}" hidden class="reginput" title="id"/>
            <input type="text" name="nameType" value="${filmMember.type.name}" hidden class="reginput" title="id"/>
            <input type="text" name="photo" value="${filmMember.photo}" hidden class="reginput"
                   title="photo"/>

            <div class="reg_text">
                <img class="img" src="${filmMember.photo}"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="reg.surname" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="surname" class="reginput" value="${filmMember.surname}" required title="surname"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="reg.name" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="name" class="reginput" value="${filmMember.name}" required title="name"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="reg.patronymic" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="patronymic" class="reginput" value="${filmMember.patronymic}" required title="patronymic"/>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="reg.birthdate" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <input type="text" name="birthdate" class="reginput" value="${filmMember.birthDate}"
                       pattern="[0-9]{4}-[0-1]{1}[0-9]{1}-[0-9]{2}" required title="yyyy-mm-dd"/>
            </div>

            <div class="reg_text">
                <button type="submit">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="member.edit" bundle="${lang}"/>
                </button>
            </div>
        </form>
    </c:if>
</div>