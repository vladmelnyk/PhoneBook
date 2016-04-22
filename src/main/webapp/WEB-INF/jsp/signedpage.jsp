<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file='header.jsp'%>
<body>


<h1>Congratulations!</h1>
<p>You have been registered at <%= new java.util.Date() %></p>

<c:url var="mainUrl" value="/main" />
<p>Return to <a href="${mainUrl}">Main Page</a></p>

</body>
</html>