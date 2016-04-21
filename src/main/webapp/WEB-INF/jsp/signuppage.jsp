

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file='header.jsp'%>
<body>

<h1>Create New Contact</h1>
<c:url var="saveUrl" value="/main/signup" />
<form:form modelAttribute="userAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="login">Login:</form:label></td>
            <td><form:input path="login"/></td>
        </tr>
        <tr>
            <td><form:label path="password">Password:</form:label></td>
            <td><form:input path="password"/></td>
        </tr>
        <tr>
            <td><form:label path="firstName">First Name:</form:label></td>
            <td><form:input path="firstName"/></td>
        </tr>

        <tr>
            <td><form:label path="middleName">Middle Name</form:label></td>
            <td><form:input path="middleName"/></td>
        </tr>

        <tr>
            <td><form:label path="lastName">Last Name</form:label></td>
            <td><form:input path="lastName"/></td>
        </tr>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </table>

    <input type="submit" value="Save" />
</form:form>

</body>
</html>