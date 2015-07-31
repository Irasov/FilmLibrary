<%@tag description="block news tamlate" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<div class="news">
    <h2>
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="site.block.news" bundle="${lang}"/>
    </h2>
    <p>Здесь будут новости</p>
</div>
