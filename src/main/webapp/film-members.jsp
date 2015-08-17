<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="filmMembersView" type="java.util.List"--%>
<%--@elvariable id="filmMemberView" type="com.epam.irasov.filmlibrary.entity.FilmMember"--%>

<html>
<head>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
  <title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="i18n" var="lang"/>
    <fmt:message key="site.film.members" bundle="${lang}"/>
  </title>
</head>
<body>
<div class="wrapper">
  <fl:header/>
  <fl:navigation/>
  <div class="container">
    <c:if test="${not empty filmMembersView}">
      <c:forEach items="${filmMembersView}" var="filmMemberView">
        <div class="films">
          <form action="${pageContext.request.contextPath}/controller?action=selectMemberView" method="post">
            <div class="reg_text">
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="user.surname" bundle="${lang}"/>
            </div>
            <div class="reg_input">
              <input type="text" name="surname" value="${filmMemberView.surname}" title="filmMember surname" readonly/>
            </div>
            <div class="reg_text">
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="user.patronymic" bundle="${lang}"/>
            </div>
            <div class="reg_input">
              <input type="text" name="patronymic" value="${filmMemberView.patronymic}" title="filmMember premiere"
                     readonly/>
            </div>
            <div class="reg_text">
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="user.name" bundle="${lang}"/>
            </div>
            <div class="reg_input">
              <input type="text" name="nane" value="${filmMemberView.name}" title="filmMember name" readonly/>
            </div>
            <div class="reg_text">
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="user.birthDate" bundle="${lang}"/>
            </div>
            <div class="reg_input">
              <input type="text" name="birthDate" value="${filmMemberView.birthDate}" title="filmMember name" readonly/>
            </div>
            <input type="text" name="idMemberView" value="${filmMemberView.id}" title="filmMember name" hidden/>
            <div class="reg_text">
              <button type="submit">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="full.information" bundle="${lang}"/>
              </button>
            </div>
          </form>
        </div>
      </c:forEach>
    </c:if>
  </div>
  <fl:footer/>
</div>
</body>
</html>
