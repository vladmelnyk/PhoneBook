<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file='header.jsp'%>
<body>

<h1>Sign in</h1>

<c:if test="${param.error ne null}">
    <div>
        Invalid username and password.
    </div>
</c:if>
<c:if test="${param.logout ne null}">
    <div>
        You have been logged out.
    </div>
</c:if>
<form action="/main/login" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <div><input type="submit" value="Sign In"/></div>
</form>
</body>
</html>
