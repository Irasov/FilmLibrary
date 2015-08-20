<%@tag description="remove news from list" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="newsBlock" type="com.epam.irasov.filmlibrary.entity.NewsBlock"--%>
<%--@elvariable id="newsInBlock" type="java.util.List"--%>
<div class="add_member_film">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="block.delete" bundle="${lang}"/>
    </div>
    <form action="${pageContext.request.contextPath}/controller?action=blockRemoveNews" method="post">
        <div class="reg_text">
            <div>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="select.newss" bundle="${lang}"/>
            </div>
            <select name="idNews" size="10" required  multiple title="select news">
                <c:forEach items="${newsBlock.news}" var="news">
                    <option value="${news.id}">${news.name}(${news.date})</option>
                </c:forEach>
            </select>
        </div>
        <div class="reg_text">
            <button type="submit">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="button.remove" bundle="${lang}"/>
            </button>
        </div>
    </form>
</div>