package Infraestructure.Persistence;

/**
 *
 * @author HP
 */
//import Business.Exceptions.DuplicateUserException;
//import Business.Exceptions.UserNotFoundException;
import Business.Exceptions.UserNotFoundException;
import Domain.Model.Donaciones;
import Infraestructure.Database.ConnectionDbMySql;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonacionesCrud {
    
   public List<Donaciones> getAllDonaciones() {
    List<Donaciones> donacionesList = new ArrayList<>();
    String query = "SELECT * FROM donaciones";
    
    try {
        Connection con = ConnectionDbMySql.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            donacionesList.add(
                new Donaciones(
                    rs.getInt("id"),
                    rs.getString("monto"),
                    rs.getString("metodo"),
                    rs.getString("numero_recibo"),
                    rs.getString("fecha_donacion"),
                    rs.getInt("usuario_id")
                )
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return donacionesList;
}
   
 
// Método para obtener el usuario_id a partir de la cédula
private int getUsuarioIdByCedula(String cedula, Connection con) throws SQLException {
    String query = "SELECT id FROM estudiantes WHERE cedula = ?";
    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setString(1, cedula);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");  // Retorna el id del usuario
        } else {
            return -1;  // Retorna -1 si no se encuentra el usuario
        }
    }
}

 
    
 


public List<Donaciones> getDonacionesByCedula(String cedula) throws SQLException {
    List<Donaciones> donaciones = new ArrayList<>();
    
    String query = "SELECT d.id, d.monto, d.metodo, d.numero_recibo, d.fecha_donacion, d.usuario_id " +
                   "FROM donaciones d " +
                   "JOIN estudiantes e ON e.id = d.usuario_id " +
                   "WHERE e.cedula = ?";
    
    try (Connection con = ConnectionDbMySql.getConnection();
         PreparedStatement stmt = con.prepareStatement(query)) {
        
        stmt.setString(1, cedula);
        ResultSet rs = stmt.executeQuery();
        
        if (!rs.next()) {
            System.out.println("No se encontraron donaciones para la cédula: " + cedula);
        } else {
            do {
                Donaciones donacion = new Donaciones(
                    rs.getInt("id"),
                    rs.getString("monto"),
                    rs.getString("metodo"),
                    rs.getString("numero_recibo"),
                    rs.getString("fecha_donacion"),
                    rs.getInt("usuario_id")
                );
                donaciones.add(donacion);
            } while (rs.next());
        }
    }
    return donaciones;
}


   
}
