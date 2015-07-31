<%@tag description="block new films tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<div class="films">
    <h2>
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="site.new.films" bundle="${lang}"/>
    </h2>
    <p>Здесь будут фильмы</p>
</div>