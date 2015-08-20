<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="selectedAction" type="java.lang.String"--%>
<%--@elvariable id="messageBlockFilms" type="java.lang.String"--%>
<%--@elvariable id="filmsInBlock" type="java.util.List"--%>
<%--@elvariable id="filmsBlock" type="com.epam.irasov.filmlibrary.entity.Film"--%>
<%--@elvariable id="messageErrorBlockFilms" type="java.lang.String"--%>
<html>
<head>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
  <title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="i18n" var="lang"/>
    <fmt:message key="site.block.film" bundle="${lang}"/>
  </title>
</head>
<body>
<div class="wrapper">
  <fl:header/>
  <fl:navigation/>
  <div class="container">
    <div class="operation">
      <div class="selected">
        <div class="select">
          <form action="${pageContext.request.contextPath}/controller?action=selectedOperationFilmBlock"
                method="post">
            <p><input type="radio" name="selected" value="1" checked title="add news"/>
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="block.film.add" bundle="${lang}"/></p>

            <p><input type="radio" name="selected" value="2" title="remove"/>
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="block.film.delete" bundle="${lang}"/></p>
            <button type="submit">
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="site.select" bundle="${lang}"/>
            </button>

            <c:if test="${not empty messageBlockFilms}">
              <div class="complete">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="${messageBlockFilms}" bundle="${lang}"/>
              </div>
            </c:if>
          </form>
        </div>
      </div>
      <c:if test="${selectedAction==1}">
        <c:if test="${empty filmsInBlock}">
          <div class="error">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="${messageErrorBlockFilms}" bundle="${lang}"/>
          </div>
        </c:if>
        <c:if test="${not empty filmsInBlock}">
          <fl:addFilmsBlock/>
        </c:if>
      </c:if>
      <c:if test="${selectedAction==2}">
        <c:if test="${not empty filmsBlock}">
          <fl:removeFilmsBlock/>
        </c:if>
        <c:if test="${empty filmsInBlock}">
          <div class="error">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="${messageErrorBlockFilms}" bundle="${lang}"/>
          </div>
        </c:if>
      </c:if>
    </div>
    <div class="user_menu"><fl:user_menu/></div>
  </div>
  <fl:footer/>
</div>
</body>
</html>