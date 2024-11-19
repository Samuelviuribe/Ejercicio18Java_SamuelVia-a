<%-- 
    Document   : DonacionesControllers
    Created on : 18/11/2024, 9:25:43?p. m.
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
    // Aquí podrías agregar un mensaje de error si no se pasa la cédula
    System.out.println("Cédula no recibida correctamente.");
} else {
    System.out.println("Cédula recibida: " + cedula); // Verifica si la cédula se recibe correctamente
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


