<%-- 
    Document   : shoppingList
    Created on : Mar 1, 2021, 11:54:46 AM
    Author     : 468181
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello ${username}</p>
        <p><a href="ShoppingList?action=logout">Log Out</a></p>
        <h2>List</h2>
        <form action="" method="POST">
            Add item: <input type="text" name="listitem">
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add item">
        </form>

        <c:if test="${listitems.size() > 0}">
            <form action="" method="POST">
                <c:forEach var="item" items="${listitems}">
                    <input type="radio" name="deleteitem" value="${item}">${item}<br>
                </c:forEach>
                <input type="hidden" name="action" value="delete">
                <p><input type="submit" value="Delete"></p>
            </form>
        </c:if>


    </body>
</html>
