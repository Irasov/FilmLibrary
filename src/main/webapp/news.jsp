<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="newsView" type="java.util.List"--%>
<%--@elvariable id="itemView" type="com.epam.irasov.filmlibrary.entity.News"--%>
<%--@elvariable id="var" type="java.lang.Integer"--%>

<html>
<head>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
  <title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="i18n" var="lang"/>
    <fmt:message key="site.news" bundle="${lang}"/>
  </title>
</head>
<body>
<div class="wrapper">
  <fl:header/>
  <fl:navigation/>
  <div class="container">
    <c:if test="${not empty newsView}">
      <c:forEach items="${newsView}" var="itemView">
        <div class="film">
          <form action="${pageContext.request.contextPath}/controller?action=selectNewsView" method="post">
            <div class="reg_input">
              <img src="${itemView.image}" class="img_news"/>
            </div>
            <div class="reg_text">
                ${itemView.date}
            </div>
            <div class="reg_text">
                ${itemView.name}
              </div>
            <input type="text" name="id" value="${itemView.id}" title="" hidden/>
            <div class="reg_text">
              <button type="submit">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="full.information" bundle="${lang}"/>
              </button>
            </div>
            <input type="text" name="end" value="${var=var+1}" title="film name" hidden/>
          </form>
        </div>
      </c:forEach>
    </c:if>
  </div>
  <c:if test="${empty newsView}">
    <fl:footer/>
  </c:if>
</div>
</body>
</html>
