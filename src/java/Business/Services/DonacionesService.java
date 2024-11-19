package Business.Services;

/**
 *
 * @author HP
 */

import Domain.Model.Donaciones;
import Business.Exceptions.UserNotFoundException;
import Business.Exceptions.DuplicateUserException;
import Infraestructure.Database.ConnectionDbMySql;
import Infraestructure.Persistence.UserCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DonacionesService {
    private int getUsuarioIdByCedula(String cedula) throws SQLException, UserNotFoundException {
    String query = "SELECT id FROM estudiantes WHERE id = ?";
    
    try (Connection con = ConnectionDbMySql.getConnection();
         PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setString(1, cedula);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        } else {
            throw new UserNotFoundException("No se encontró un usuario con la cédula: " + cedula);
        }
    }
}
    
    public List<Donaciones> getDonacionesByCedula(String cedula) throws SQLException {
    List<Donaciones> donacionesList = new ArrayList<>();
    String query = "SELECT * FROM donaciones WHERE usuario_id = (SELECT id FROM estudiantes WHERE cedula = ?)";
    
    try (Connection con = ConnectionDbMySql.getConnection();
         PreparedStatement stmt = con.prepareStatement(query)) {
        
        stmt.setString(1, cedula); // Pasamos la cédula como parámetro
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Donaciones donacion = new Donaciones(
                rs.getString("id"),
                rs.getString("monto"),
                rs.getString("metodo"),
                rs.getString("numero_recibo"),
                rs.getString("fecha_donacion"),
                cedula // Puedes pasar la cédula aquí o directamente usar el id del usuario
            );
            donacionesList.add(donacion);
        }
    }
    
    return donacionesList;
}


}
