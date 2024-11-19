package Infraestructure.Persistence;

import Business.Exceptions.UserNotFoundException;
import Domain.Model.Donaciones;
import Infraestructure.Database.ConnectionDbMySql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonacionesCrud {

    // Obtener todas las donaciones
    public List<Donaciones> getAllDonaciones() {
        List<Donaciones> donacionesList = new ArrayList<>();
        String query = "SELECT * FROM donaciones";

        try (Connection con = ConnectionDbMySql.getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Donaciones donacion = new Donaciones(
                        rs.getInt("id"),
                        rs.getFloat("monto"),
                        rs.getString("metodo"),
                        rs.getInt("numero_recibo"),
                        rs.getTimestamp("fecha_donacion").toString(),
                        null // Si no necesitas la cédula, puedes omitirla aquí
                );
                donacionesList.add(donacion);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener la lista de donaciones: " + e.getMessage());
            e.printStackTrace();
        }

        return donacionesList;
    }

public List<Donaciones> getDonacionesByUsuarioId(String usuarioId) {
    List<Donaciones> donaciones = new ArrayList<>();

    // Ajustamos la consulta para usar 'estudiantes_id' y obtener la cédula con JOIN
    String query = "SELECT d.id, d.monto, d.metodo, d.numero_recibo, d.fecha_donacion, e.cedula "
            + "FROM donaciones d "
            + "JOIN estudiantes e ON d.estudiantes_id = e.id "
            + "WHERE d.estudiantes_id = ?";

    try (Connection con = ConnectionDbMySql.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {

        // Usamos setInt para evitar convertir a String
        stmt.setInt(1, Integer.parseInt(usuarioId));  
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Donaciones donacion = new Donaciones(
                    rs.getInt("id"),
                    rs.getFloat("monto"),
                    rs.getString("metodo"),
                    rs.getInt("numero_recibo"),
                    rs.getTimestamp("fecha_donacion").toString(),
                    rs.getString("cedula") // Obtenemos la cédula del estudiante
            );
            donaciones.add(donacion);
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener las donaciones para el usuario con ID " + usuarioId + ": " + e.getMessage());
        e.printStackTrace();
    }

    return donaciones;
}



    // Método para obtener usuario_id por cédula (si aún es necesario)
    private int getUsuarioIdByCedula(String cedula) throws SQLException, UserNotFoundException {
        String query = "SELECT id FROM estudiantes WHERE cedula = ?";
        try (Connection con = ConnectionDbMySql.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new UserNotFoundException("No se encontró un usuario con la cédula: " + cedula);
            }
        }
    }
}
