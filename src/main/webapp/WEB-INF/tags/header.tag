<%@tag description="header tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>

<div class="header">
    <a href="${pageContext.request.contextPath}/index.jsp"><img src="img/logo.png" class="logo"></a>
        <h2>
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="site.welcome" bundle="${lang}"/>
        </h2>
</div>
