<%-- 
    Document   : register
    Created on : Feb 13, 2020, 10:46:30 PM
    Author     : 779137
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form method="POST">
            <input type="hidden" name="action" value="register" />
            Username: <input type="text" name="fldUsername" />
            <input type="submit" name="btnSubmit" value="Register name" />
        </form>
    </body>
</html>
