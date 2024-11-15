<%-- 
    Document   : create.jsp
    Created on : 12/11/2024, 9:33:30 p. m.
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Agregar Usuario</h1>
        <%-- Mensaje de eror o Exito --%>
        <% if (request.getAttribute("errorMessage") != null) { %>
        <p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
        <% } %>
        <% if (request.getAttribute("successMessage") != null) { %>
        <p style="color:green;"><%= request.getAttribute("successMessage") %></p>
        <% } %>

        <%-- Formulario para agregar usuario --%>
        <form action="<%= request.getContextPath() %>/Controllers/UserController.jsp?action=create" method="post"> 
            <label for="code">Código:</label><br>
            <input type="text" id="code" name="code" required><br><br>

            <label for="name">Nombre:</label><br>
            <input type="text" id="name" name="name" required><br><br>

            <label for="email">Email:</label><br>
            <input type="email" id="email" name="email" required><br><br>

            <label for="password">Contraseña:</label><br>
            <input type="password" id="password" name="password" required><br><br>

            <input type="submit" value="Agregar Usuario">
        </form>
        <a href="<%= request.getContextPath() %>/index.jsp">Menu Principal</a>
    </body>
</html>
