<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file='header.jsp'%>
<body>
<h1>Contacts</h1>

<c:url var="addUrl" value="/main/contacts/add" />
<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#cfc">
    <tr>
        <th>First Name</th>
        <th>Middle Name</th>
        <th>Last Name</th>
        <th>Mobile Number</th>
        <th>Phone Number</th>
        <th>Email</th>
        <th colspan="6"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${contacts}" var="contact">
        <c:url var="editUrl" value="/main/contacts/edit?id=${contact.id}" />
        <c:url var="deleteUrl" value="/main/contacts/delete?id=${contact.id}" />
        <tr>
            <td><c:out value="${contact.firstName}" /></td>
            <td><c:out value="${contact.middleName}" /></td>
            <td><c:out value="${contact.lastName}" /></td>
            <td><c:out value="${contact.mobileNumber}" /></td>
            <td><c:out value="${contact.phoneNumber}" /></td>
            <td><c:out value="${contact.email}" /></td>
            <td><a href="${editUrl}">Edit</a></td>
            <td><a href="${deleteUrl}">Delete</a></td>
            <td><a href="${addUrl}">Add</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="container">

    <div class="jumbotron" style="margin-top: 20px;">
        <h1>Devcolibri.com</h1>
        <p class="lead">
            Devcolibri - это сервис предоставляющий всем желающим возможность обучаться программированию.
        </p>
        <sec:authorize access="isAuthenticated()">
            <p>Ваш логин: <sec:authentication property="principal.username" /></p>
            <p><a class="btn btn-lg btn-danger" href="<c:url value="/main/logout" />" role="button">Выйти</a></p>

        </sec:authorize>
    </div>

    <div class="footer">
        <p>© Devcolibri 2014</p>
    </div>

</div>

<c:if test="${empty contacts}">
    There are currently no contacts in the list. <a href="${addUrl}">Add</a> a contact.
</c:if>

</body>
</html>