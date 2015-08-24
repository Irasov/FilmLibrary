<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="filmView" type="com.epam.irasov.filmlibrary.entity.Film"--%>
<%--@elvariable id="filmMembersViewIn" type="java.util.List"--%>
<%--@elvariable id="filmMemberView" type="com.epam.irasov.filmlibrary.entity.FilmMember"--%>
<%--@elvariable id="systemMember" type="com.epam.irasov.filmlibrary.entity.SystemMember"--%>
<%--@elvariable id="textError" type="java.lang.String"--%>
<%--@elvariable id="messageReview" type="java.lang.String"--%>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="site.films" bundle="${lang}"/>
    </title>
</head>
<body>
<div class="wrapper">
    <fl:header/>
    <fl:navigation/>
    <div class="container">
        <div class="films">
            <div class="film_img">
                <img class="img" src="${filmView.cover}"/>
            </div>
            <div class="film_info">
                <div class="reg_text">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="film.name" bundle="${lang}"/> ${filmView.name}
                </div>
                <div class="reg_text">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="film.tagline" bundle="${lang}"/>: ${filmView.tagLine}
                </div>
                <div class="reg_text">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="film.premiere" bundle="${lang}"/>: ${filmView.premiere}
                </div>
                <div class="reg_text">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="film.genre" bundle="${lang}"/>: ${filmView.genre}
                </div>
                <div class="reg_text">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="film.restriction.age" bundle="${lang}"/>: ${filmView.ageRestriction}+
                </div>
                <div class="reg_text">
                    <fmt:setLocale value="${locale}"/>
                    <fmt:setBundle basename="i18n" var="lang"/>
                    <fmt:message key="film.duration" bundle="${lang}"/>: ${filmView.duration}
                </div>
            </div>
            <div class="filmMember">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.member" bundle="${lang}"/>
                <c:if test="${not empty filmMembersViewIn}">
                    <div class="reg_text">
                        <form action="${pageContext.request.contextPath}/controller?action=selectMemberView"
                              method="post">
                            <div class="reg_text">
                                <select name="idMemberView" size="7" required title="select Member">
                                    <c:forEach items="${filmMembersViewIn}" var="filmMemberView">
                                        <option value="${filmMemberView.id}">${filmMemberView.surname}(${filmMemberView.type.name})</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="reg_text">
                                <button type="submit">
                                    <fmt:setLocale value="${locale}"/>
                                    <fmt:setBundle basename="i18n" var="lang"/>
                                    <fmt:message key="full.information" bundle="${lang}"/>
                                </button>
                            </div>
                        </form>
                    </div>
                </c:if>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="reg.description" bundle="${lang}"/>:
                <br/>
                <p>${filmView.description}</p>
            </div>
            <div class="reg_text">
                <fmt:setLocale value="${locale}"/>
                <fmt:setBundle basename="i18n" var="lang"/>
                <fmt:message key="film.rating" bundle="${lang}"/> ${filmView.rating.votes}
                <c:if test="${not empty systemMember}">
                    <form action="${pageContext.request.contextPath}/controller?action=incVotes" method="post">
                        <div class="reg_text">
                            <input type="text" name="idRating" value="${filmView.rating.id}" hidden class="reginput"
                                   title="id"/>
                            <input type="text" name="id" value="${filmView.id}" hidden class="reginput" title="id"/>
                            <input type="text" name="votes" value="${filmView.rating.votes}" hidden class="reginput"
                                   title="id"/>
                            <button type="submit">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="film.votes" bundle="${lang}"/>
                            </button>
                        </div>
                    </form>
                </c:if>
            </div>
            <c:if test="${not empty filmView.reviews}">
                <div class="reviews">
                    <div class="reg_text">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="user.user.reviews" bundle="${lang}"/>:
                    </div>
                    <c:forEach items="${filmView.reviews}" var="review">
                        <c:if test="${review.published==true}">
                            <div class="reg_text">
                                <div class="reg_text">
                                    <fmt:setLocale value="${locale}"/>
                                    <fmt:setBundle basename="i18n" var="lang"/>
                                    <fmt:message key="date.writing" bundle="${lang}"/> ${review.date}
                                </div>
                                <div class="reg_text">
                                    <fmt:setLocale value="${locale}"/>
                                    <fmt:setBundle basename="i18n" var="lang"/>
                                    <fmt:message key="avtor.review" bundle="${lang}"/> ${review.name}
                                </div>
                                <div class="reg_text_${review.status}">
                                    <style type="text/css" scoped>
                                        <c:if test="${review.status=='positive'}">
                                        .reg_text_positive {
                                            background-color: rgba(151, 212, 17, 0.25);
                                        }

                                        </c:if>
                                        <c:if test="${review.status=='negative'}">
                                        .reg_text_negative {
                                            background-color: rgba(255, 1, 12, 0.25);
                                        }

                                        </c:if>
                                        <c:if test="${review.status=='neutral'}">
                                        .reg_text_neutral {
                                            background-color: rgba(177, 172, 168, 0.25);
                                        }

                                        </c:if>
                                    </style>
                                        ${review.text}
                                </div>
                                <div class="reg_text">
                                    <fmt:setLocale value="${locale}"/>
                                    <fmt:setBundle basename="i18n" var="lang"/>
                                    <fmt:message key="review.rating" bundle="${lang}"/> ${review.rating.votes}
                                </div>
                                <c:if test="${not empty systemMember}">
                                    <div class="reg_text">
                                        <form action="${pageContext.request.contextPath}/controller?action=incVotes"
                                              method="post">
                                            <div class="reg_text">
                                                <input type="text" name="id" value="${filmView.id}" hidden
                                                       class="reginput"
                                                       title="id"/>
                                                <input type="text" name="idRating" value="${review.rating.id}" hidden
                                                       class="reginput" title="id"/>
                                                <input type="text" name="votes" value="${review.rating.votes}" hidden
                                                       class="reginput" title="id"/>
                                                <button type="submit">
                                                    <fmt:setLocale value="${locale}"/>
                                                    <fmt:setBundle basename="i18n" var="lang"/>
                                                    <fmt:message key="film.votes" bundle="${lang}"/>
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${not empty systemMember}">
                <div class="reg_text">
                    <div class="reg_input">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="review.message" bundle="${lang}"/>
                    </div>
                    <form action="${pageContext.request.contextPath}/controller?action=addReviews" method="post">
                        <input type="text" name="idFilm" value="${filmView.id}" hidden class="reginput"
                               title="id"/>
                        <div class="reg_input">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="select.status" bundle="${lang}"/>
                        </div>
                        <div class="reg_input">
                            <select name="status" size="4" required title="select status">
                                <option value="positive">positive</option>
                                <option value="negative">negative</option>
                                <option value="neutral">neutral</option>
                            </select>
                        </div>
                        <div class="reg_input">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="review.text" bundle="${lang}"/>
                        </div>
                        <c:if test="${not empty messageReview}">
                            <div class="complete">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="${messageReview}" bundle="${lang}"/>
                            </div>
                        </c:if>
                        <c:if test="${not empty textError}">
                            <div class="error">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="${textError}" bundle="${lang}"/>
                            </div>
                        </c:if>
                        <div class="reg_input">
                        <textarea name="reviewText" required class="review_text" rows="12" cols="100"
                                  title="text"></textarea>
                        </div>
                        <button type="submit">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="button.add" bundle="${lang}"/>
                        </button>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
    <fl:footer/>
</div>
</body>
</html>