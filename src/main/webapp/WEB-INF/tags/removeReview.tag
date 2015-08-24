<%@tag description="add film tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="reviews" type="java.util.List"--%>
<%--@elvariable id="review" type="com.epam.irasov.filmlibrary.entity.Review"--%>
<div class="reviews_op">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="review.remove" bundle="${lang}"/>
    </div>
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="select.review" bundle="${lang}"/>
    </div>
    <form action="${pageContext.request.contextPath}/controller?action=selectReview" method="post">
        <div class="reg_text">
            <select name="idReview" size="10" required title="select news">
                <c:forEach items="${reviews}" var="review">
                    <option value="${review.id}">${review.name}(${review.date})</option>
                </c:forEach>
            </select>
        </div>
        <div class="reg_text">
            <button type="submit">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="button.select" bundle="${lang}"/>
            </button>
        </div>
    </form>
    <c:if test="${not empty review}">
        <form action="${pageContext.request.contextPath}/controller?action=removeReview" method="post">
            <input type="text" name="id" value="${review.id}" hidden class="reginput" title="id"/>
            <input type="text" name="idRating" value="${review.rating.id}" hidden class="reginput" title="id"/>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="review.text" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                    ${review.text}
            </div>
            <div class="reg_text">
                <button type="submit">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="button.remove" bundle="${lang}"/>
                </button>
            </div>
        </form>
    </c:if>
</div>