<%@tag description="block news tamlate" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="newsBlock" type="com.epam.irasov.filmlibrary.entity.NewsBlock"--%>
<div class="news_block">
    <c:if test="${not empty newsBlock}">
        <div class="reg_text">
            <h3>
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="${newsBlock.name}" bundle="${lang}"/>
            </h3>
            <c:forEach items="${newsBlock.news}" var="item">
                <form action="${pageContext.request.contextPath}/controller?action=selectNewsView" method="post">
                    <input name="id" hidden value="${item.id}" title=""/>

                    <div class="reg_text">
                            ${item.date}: ${item.name}
                        <br/>
                        <button type="submit">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="button.read" bundle="${lang}"/>
                        </button>
                    </div>
                </form>
            </c:forEach>
        </div>
    </c:if>
</div>
