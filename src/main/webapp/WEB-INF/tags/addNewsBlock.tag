<%@tag description="add news in list" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="newsBlock" type="com.epam.irasov.filmlibrary.entity.NewsBlock"--%>
<%--@elvariable id="newsInBlock" type="java.util.List"--%>
<%--@elvariable id="messageRepeat" type="java.lang.String"--%>
<div class="add_member_film">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="block.add" bundle="${lang}"/>
    </div>
    <c:if test="${not empty messageRepeat}">
        <div class="error">
            <fmt:setLocale value="${locale}"/>
            <fmt:setBundle basename="i18n" var="lang"/>
            <fmt:message key="${messageRepeat}" bundle="${lang}"/>
        </div>
    </c:if>
    <form action="${pageContext.request.contextPath}/controller?action=blockAddNews" method="post">
        <div class="reg_text">
            <div>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="select.news" bundle="${lang}"/>
            </div>
            <select name="idNews" size="10" required title="select News">
                <c:forEach items="${newsInBlock}" var="news">
                    <option value="${news.id}">${news.name}(${news.date})</option>
                </c:forEach>
            </select>
        </div>
        <div class="reg_text">
            <button type="submit">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="button.add" bundle="${lang}"/>
            </button>
        </div>
    </form>
</div>