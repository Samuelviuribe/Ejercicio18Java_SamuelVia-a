<%-- 
    Document   : DonacionesControllers
    Created on : 18/11/2024, 9:25:43?p.�m.
    Author     : HP
--%>

<%@ page import="java.util.List" %>
<%@ page import="Domain.Model.Donaciones" %>

<%
    List<Donaciones> donaciones = (List<Donaciones>) request.getAttribute("donaciones");
    if (donaciones != null && !donaciones.isEmpty()) {
%>

<%
    String cedula = request.getParameter("cedula");
if (cedula == null || cedula.isEmpty()) {
    // Aqu� podr�as agregar un mensaje de error si no se pasa la c�dula
    System.out.println("C�dula no recibida correctamente.");
} else {
    System.out.println("C�dula recibida: " + cedula); // Verifica si la c�dula se recibe correctamente
}

%>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Monto</th>
                    <th>Metodo</th>
                    <th>Numero de Recibo</th>
                    <th>Fecha de Donacion</th>
                </tr>
            </thead>
            <tbody>
                <% for (Donaciones donacion : donaciones) { %>
                    <tr>
                        <td><%= donacion.getId() %></td>
                        <td><%= donacion.getMonto() %></td>
                        <td><%= donacion.getMedoto() %></td>
                        <td><%= donacion.getNumero_recibo() %></td>
                        <td><%= donacion.getFecha_donacion() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
<% } else { %>
        <p>No se encontraron donaciones para este usuario.</p>
<% } %>


