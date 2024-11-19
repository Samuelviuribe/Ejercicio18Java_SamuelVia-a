<%-- 
    Document   : DonacionesControllers
    Created on : 18/11/2024, 9:25:43?p. m.
    Author     : HP
--%>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Model.Donaciones" %>
<%@ page import="Infraestructure.Persistence.DonacionesCrud" %>

<%
    String action = request.getParameter("action");

    if ("searchByUsuarioId".equals(action)) {
        String usuarioId = request.getParameter("usuario_id");

        // Llama al método adecuado en tu DAO o servicio para obtener las donaciones
        DonacionesCrud donacionesCrud = new DonacionesCrud();
        List<Donaciones> donacionesList = donacionesCrud.getDonacionesByUsuarioId(usuarioId); // Cambiar el método para usar usuario_id

        // Mostrar las donaciones directamente en la página
        if (donacionesList != null && !donacionesList.isEmpty()) {
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
                <% for (Donaciones donacion : donacionesList) { %>
                    <tr>
                        <td><%= donacion.getId() %></td>
                        <td><%= donacion.getMonto() %></td>
                        <td><%= donacion.getMetodo() %></td>
                        <td><%= donacion.getNumeroRecibo() %></td>
                        <td><%= donacion.getFechaDonacion() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>

<%      } else { %>
<%= request.getParameter("usuario_id") %>
            <p>No se encontraron donaciones para este usuario.</p>
<%      }
    } else { %>
        <p>Acción no válida.</p>
<%  } %>

