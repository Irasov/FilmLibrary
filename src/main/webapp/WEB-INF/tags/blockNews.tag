<%@tag description="block news tamlate" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="newsBlock" type="com.epam.irasov.filmlibrary.entity.NewsBlock"--%>
<div class="news">
    <h2>
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="site.block.news" bundle="${lang}"/>
    </h2>
    <c:if test="${not empty newsBlock}">
        <p>block.news.name: ${newsBlock.name}</p>
        <c:forEach items="${newsBlock.news}" var="news">
            <div style="padding: 0 0 1% 5%">
                <p>news.name ${news.name}</p>
                <p>news.date ${news.date}</p>
                <p>news.text ${news.text}</p>
                <p>news.imge ${news.image}</p>
            </div>
        </c:forEach>
    </c:if>
</div>
