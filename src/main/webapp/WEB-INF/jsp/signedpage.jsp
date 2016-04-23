<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file='header.jsp' %>
<body>

<h1>Congratulations!</h1>
<div class="success callout">
    <p>You have been successfully registered at <%= new java.util.Date() %>
    </p>
</div>
<c:url var="mainUrl" value="/main"/>
</body>
</html>