<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="itemView" type="com.epam.irasov.filmlibrary.entity.News"--%>

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
    <div class="films">
      <div class="reg_input">
        ${itemView.date}
      </div>
      <div class="reg_input">
        ${itemView.name}
      </div>
      <div class="reg_input">
        <img class="img" src="${itemView.image}"/>
      </div>
      <div class="reg_input">
        ${itemView.text}
      </div>
    </div>
  </div>
  <fl:footer/>
</div>
</body>
</html>

