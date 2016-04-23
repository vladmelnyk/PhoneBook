<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file='header.jsp' %>
<body>

<h1 align="center">Sign in</h1>

<c:if test="${param.error ne null}">
    <div class="alert callout">
        <div class="small-3 small-centered columns ">
            <h6>Invalid username or password</h6>
        </div>
    </div>
</c:if>
<c:if test="${param.logout ne null}">
    <div class="alert callout">
        <div class="small-3 small-centered columns ">
            You have been logged out.
        </div>
    </div>
</c:if>
<form action="/main/login" method="post">
<div class="row">
    <div class="small-3 small-centered columns ">
        <label> Username : <input type="text" name="username"/> </label>
    </div>
</div>
<div class="row">
    <div class="small-3 small-centered columns ">
        <label> Password: <input type="password" name="password"/> </label>
    </div>
</div>
<div class="row">
    <div class="small-3 small-centered columns ">
        <div class="input-group-button">

                <input type="submit" class="button" value="Sign In"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
        <div class="input-group-button">
            <form action="/main/signup" method="get">
                <input type="submit" class="button secondary" value="Sign Up"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</div>

</body>
</html>
