<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file='header.jsp' %>
<body>
<h1 align="center">Welcome!</h1>
<br>
<div  class="callout primary ">
    <p align="center">Click <a href="<spring:url value='/main/login' />">here</a> to login</p>
    <p align="center">Click <a href="<spring:url value='/main/signup' />">here</a> to register</p>
</div>
</body>
</html>