<%-- 
    Document   : agregar
    Created on : 7/11/2024, 4:40:24 p. m.
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>Iniciar Sesión</h1>
        <%-- Mensaje de error en caso de credenciales incorrectas --%>
        <% if (request.getAttribute("errorMessage") != null) { %> 
        <p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
        <% } %> <%-- Formulario de Login --%> 
        <form action="<%= request.getContextPath() %>/Controllers/UserController.jsp?action=authenticate" method="post"> 
            
            <label for="email">Email:</label><br> 
            <input type="email" id="email" name="email" required><br><br> 
            
            <label for="password">Contraseña:</label><br> 
            <input type="password" id="password" name="password" required><br><br> 
            
            <input type="submit" value="Iniciar Sesión"> 
        </form> <br> 
        
        <a href="<%= request.getContextPath() %>../index.jsp">Volver a la página de inicio</a> 
    </body>
</html>