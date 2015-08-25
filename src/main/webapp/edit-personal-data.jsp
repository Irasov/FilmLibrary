<%--@elvariable id="systemMember" type="com.epam.irasov.filmlibrary.entity.SystemMember"--%>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="fileName" type="java.lang.String"--%>
<%--@elvariable id="emailError" type="java.lang.String"--%>
<%--@elvariable id="messageEdit" type="java.lang.String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
  <title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="i18n" var="lang"/>
    <fmt:message key="edit.personal.data" bundle="${lang}"/>
  </title>
</head>
<body>
<div class="wrapper">
  <fl:header/>
  <fl:navigation/>
  <div class="container">
    <div class="edit_member">
      <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="edit.personal.data" bundle="${lang}"/>
      </div>
      <c:if test="${not empty messageEdit}">
        <div class="complete">
          <fmt:setLocale value="${locale}"/>
          <fmt:setBundle basename="i18n" var="lang"/>
          <fmt:message key="${messageEdit}" bundle="${lang}"/>
        </div>
      </c:if>
        <form action="${pageContext.request.contextPath}/controller?action=editImage&page=edit-personal-data" method="post"
              enctype="multipart/form-data">
          <div class="reg_input">
            <input type="file" class="reginput" name="file" accept="image/jpeg,image/png"/>
          </div>
          <input type="text" name="strar" value="${systemMember.photo}" hidden class="reginput"
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
                  url="/controller?action=editUser&name=${systemMember.name}&id=${systemMember.id}&surname=${systemMember.surname}&patronymic=${systemMember.patronymic}&birthdate=${systemMember.birthDate}&photo=img/site/${fileName}&idType=${systemMember.type.id}&nameType=${systemMember.type.name}&email=${systemMember.email}"/>
        </c:if>
        <form action="${pageContext.request.contextPath}/controller?action=editUser" method="post">
          <input type="text" name="id" value="${systemMember.id}" hidden class="reginput" title="id"/>
          <input type="text" name="idType" value="${systemMember.type.id}" hidden class="reginput" title="id"/>
          <input type="text" name="nameType" value="${systemMember.type.name}" hidden class="reginput" title="id"/>
          <input type="text" name="photo" value="${systemMember.photo}" hidden class="reginput"
                 title="photo"/>
          <input type="text" name="login" value="${systemMember.login}" hidden class="reginput" title="login"/>
          <input type="text" name="password" value="${systemMember.password}" hidden class="reginput" title="password"/>

          <div class="reg_text">
            <img class="img" src="${systemMember.photo}"/>
          </div>
          <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.surname" bundle="${lang}"/>
          </div>
          <div class="reg_input">
            <input type="text" name="surname" class="reginput" value="${systemMember.surname}" required title="surname"/>
          </div>
          <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.name" bundle="${lang}"/>
          </div>
          <div class="reg_input">
            <input type="text" name="name" class="reginput" value="${systemMember.name}" required title="name"/>
          </div>
          <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.patronymic" bundle="${lang}"/>
          </div>
          <div class="reg_input">
            <input type="text" name="patronymic" class="reginput" value="${systemMember.patronymic}" required title="patronymic"/>
          </div>
          <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.birthdate" bundle="${lang}"/>
          </div>
          <div class="reg_input">
            <input type="text" name="birthdate" class="reginput" value="${systemMember.birthDate}"
                   pattern="[0-9]{4}-[0-1]{1}[0-9]{1}-[0-9]{2}" required title="yyyy-mm-dd"/>
          </div>
          <div class="reg_text">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="reg.email" bundle="${lang}"/>
          </div>
          <c:if test="${not empty emailError}">
            <div class="error">
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="${emailError}" bundle="${lang}"/>
            </div>
          </c:if>
          <div class="reg_input">
            <input type="text" name="email"  value="${systemMember.email}" class="reginput" required title="email@example.com"/>
            <input type="text" name="prevEmail"  value="${systemMember.email}" class="reginput" hidden title="email@example.com"/>
          </div>
          <div class="reg_text">
            <button type="submit">
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="member.edit" bundle="${lang}"/>
            </button>
          </div>
        </form>
    </div>
    <div class="user_menu"><fl:user_menu/></div>
  </div>
  <fl:footer/>
</div>
</body>
</html>
