<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="locale" type="java.lang.String"--%>
<%--@elvariable id="selectedAction" type="java.lang.String"--%>
<%--@elvariable id="messageFilmMember" type="java.lang.String"--%>
<%--@elvariable id="fileError" type="java.lang.String"--%>
<%--@elvariable id="messageError" type="java.lang.String"--%>
<%--@elvariable id="filmMembers" type="java.util.List"--%>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>
        <fmt:setLocale value="${locale}"/>
        <fmt:setBundle basename="i18n" var="lang"/>
        <fmt:message key="site.member" bundle="${lang}"/>
    </title>
</head>
<body>
<div class="wrapper">
    <fl:header/>
    <fl:navigation/>
    <div class="container">
        <div class="operation">
            <div class="selected">
                <div class="select">
                    <form action="${pageContext.request.contextPath}/controller?action=selectedMemberOperation" method="post">
                        <p><input type="radio" name="selected" value="1" checked title="add member"/>
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="add.member" bundle="${lang}"/></p>
                        <p><input type="radio" name="selected" value="2" title="remove"/>
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="edit.member" bundle="${lang}"/></p>
                        <p><input type="radio" name="selected" value="3" title="count"/>
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="remove.member" bundle="${lang}"/></p>
                        <button type="submit">
                            <fmt:setLocale value="${locale}"/>
                            <fmt:setBundle basename="i18n" var="lang"/>
                            <fmt:message key="site.select" bundle="${lang}"/>
                        </button>
                        <c:if test="${not empty messageFilmMember}">
                            <div class="complete">
                                <fmt:setLocale value="${locale}"/>
                                <fmt:setBundle basename="i18n" var="lang"/>
                                <fmt:message key="${messageFilmMember}" bundle="${lang}"/>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
           <c:if test="${selectedAction==1}">
                <fl:addMember/>
            </c:if>
             <c:if test="${selectedAction==2}">
                <c:if test="${not empty filmMembers}">
                    <fl:editMember/>
                </c:if>
                <c:if test="${empty filmMembers}">
                    <div class="error">
                        <fmt:setLocale value="${locale}"/>
                        <fmt:setBundle basename="i18n" var="lang"/>
                        <fmt:message key="${messageError}" bundle="${lang}"/>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${selectedAction==3}">
                 <c:if test="${not empty filmMembers}">
                     <fl:removeFilmMember/>
                 </c:if>
                 <c:if test="${empty filmMembers}">
                     <div class="error">
                         <fmt:setLocale value="${locale}"/>
                         <fmt:setBundle basename="i18n" var="lang"/>
                         <fmt:message key="${messageError}" bundle="${lang}"/>
                     </div>
                 </c:if>
            </c:if>
        </div>
        <div class="user_menu"><fl:user_menu/></div>
    </div>
    <fl:footer/>
</div>
</body>
</html>