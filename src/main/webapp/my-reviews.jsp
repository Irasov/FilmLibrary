<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="selectedAction" type="java.lang.String"--%>
<%--@elvariable id="messageReview" type="java.lang.String"--%>
<%--@elvariable id="messageErrorReview" type="java.lang.String"--%>
<%--@elvariable id="myReviews" type="java.util.List"--%>
<html>
<head>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
  <title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="i18n" var="lang"/>
    <fmt:message key="user.reviews" bundle="${lang}"/>
  </title>
</head>
<body>
<div class="wrapper">
  <fl:header/>
  <fl:navigation/>
  <div class="container">
    <div class="operation_review">
      <div class="selected">
        <div class="select">
          <form action="${pageContext.request.contextPath}/controller?action=selectedMyReviewOperation"
                method="post">
            <p><input type="radio" name="selected" value="1" checked title="published"/>
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="review.edit" bundle="${lang}"/></p>
            <p><input type="radio" name="selected" value="2" title="edit"/>
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="review.remove" bundle="${lang}"/></p>
            <button type="submit">
              <fmt:setLocale value="${locale}"/>
              <fmt:setBundle basename="i18n" var="lang"/>
              <fmt:message key="site.select" bundle="${lang}"/>
            </button>
            <c:if test="${not empty messageReview}">
              <div class="complete">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="${messageReview}" bundle="${lang}"/>
              </div>
            </c:if>
            <c:if test="${not empty messageErrorReview}">
              <div class="error">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="${messageErrorReview}" bundle="${lang}"/>
              </div>
            </c:if>
          </form>
        </div>
      </div>
      <c:if test="${selectedAction==1}">
        <fl:editReviev/>
      </c:if>
      <c:if test="${selectedAction==2}">
        <c:if test="${not empty myReviews}">
          <fl:removeMyReview/>
        </c:if>
      </c:if>
    </div>
    <div class="user_menu"><fl:user_menu/></div>
  </div>
  <fl:footer/>
</div>
</body>
</html>