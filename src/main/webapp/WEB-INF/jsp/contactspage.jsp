<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file='header.jsp' %>
<body>
<h1>Contacts</h1>

<c:url var="addUrl" value="/contacts/createview"/>
<c:url var="editUrl" value="/contacts/editview"/>
<c:url var="deleteUrl" value="/contacts/delete"/>
<c:url var="logoutUrl" value="/main/logout"/>
<table style="border: 1px solid; width: 800px; text-align:center">
    <thead style="background:#cfc">
    <tr>
        <th>First Name</th>
        <th>Middle Name</th>
        <th>Last Name</th>
        <th>Mobile Number</th>
        <th>Phone Number</th>
        <th>Email</th>
        <th colspan="8"></th>
    </tr>
    <tr>
        <form action="/contacts" method="POST">
            <c:forEach var="i" begin="0" end="8">
                <th>
                    <c:choose>
                        <c:when test="${i==0}">
                            <input type="TEXT" name="first_name_exp">
                        </c:when>
                        <c:when test="${i==3}">
                            <input type="TEXT" name="mobile_number_exp">
                        </c:when>
                        <c:when test="${i==8}">
                            <input type="SUBMIT" value="Search">
                        </c:when>
                    </c:choose>
                </th>
            </c:forEach>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${contacts}" var="contact">

        <tr>
            <td><c:out value="${contact.firstName}"/></td>
            <td><c:out value="${contact.middleName}"/></td>
            <td><c:out value="${contact.lastName}"/></td>
            <td><c:out value="${contact.mobileNumber}"/></td>
            <td><c:out value="${contact.phoneNumber}"/></td>
            <td><c:out value="${contact.email}"/></td>
            <td>
                <form name="submitForm" method="GET" action="${editUrl}">
                    <input type="hidden" name="id" value="${contact.id}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" value="Edit"/>
                </form>
            </td>
            <td>
                <form name="submitForm" method="POST" action="${deleteUrl}">
                    <input type="hidden" name="id" value="${contact.id}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
            <td>
                <form name="submitForm" method="GET" action="${addUrl}">
                    <input type="hidden" name="id" value="${contact.id}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" value="Add"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<form name="submitForm" method="POST" action="${logoutUrl}">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="logout"/>
</form>


<c:if test="${empty contacts}">
    There are currently no contacts in the list. <a href="${addUrl}">Add</a> a contact.
</c:if>

</body>
</html>