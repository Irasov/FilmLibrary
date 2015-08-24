<%@tag description="add film tamlate" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="myReviews" type="java.util.List"--%>
<%--@elvariable id="myReview" type="com.epam.irasov.filmlibrary.entity.Review"--%>
<%--@elvariable id="textError" type="java.lang.String"--%>
<div class="reviews_op">
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="review.edit" bundle="${lang}"/>
    </div>
    <div class="reg_text">
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="select.review" bundle="${lang}"/>
    </div>
    <form action="${pageContext.request.contextPath}/controller?action=selectMyReview" method="post">
        <div class="reg_text">
            <select name="idReview" size="10" required title="select news">
                <c:forEach items="${myReviews}" var="review">
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
    <c:if test="${not empty myReview}">
        <form action="${pageContext.request.contextPath}/controller?action=editReview" method="post">
            <input type="text" name="id" value="${myReview.id}" hidden class="reginput" title="id"/>

            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="review.text" bundle="${lang}"/>
            </div>
            <div class="reg_input">
                <c:if test="${not empty textError}">
                    <div class="error">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="${textError}" bundle="${lang}"/>
                    </div>
                </c:if>
                <div class="reg_input">
                        <textarea name="reviewText" required class="review_text" rows="12" cols="100"
                                  title="text">${myReview.text}</textarea>
                </div>
            </div>
            <div class="reg_text">
                <button type="submit">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="button.edit" bundle="${lang}"/>
                </button>
            </div>
        </form>
    </c:if>
</div>