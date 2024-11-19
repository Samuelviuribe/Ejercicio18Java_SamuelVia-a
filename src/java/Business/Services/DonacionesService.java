package Business.Services;
 
/**
 *
 * @author HP
 */

import Domain.Model.Donaciones;
import Business.Exceptions.UserNotFoundException;
import Infraestructure.Database.ConnectionDbMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonacionesService {

    // Método para obtener las donaciones por usuario_id
    public List<Donaciones> getDonacionesByUsuarioId(int usuarioId) throws SQLException {
        List<Donaciones> donacionesList = new ArrayList<>();
        String query = "SELECT * FROM donaciones WHERE usuario_id = ?";  // Usa usuario_id en lugar de buscar por cédula

        try (Connection con = ConnectionDbMySql.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setInt(1, usuarioId);  // Usar usuarioId como parámetro
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Donaciones donacion = new Donaciones(
                    rs.getInt("id"),
                    rs.getFloat("monto"),
                    rs.getString("metodo"),
                    rs.getInt("numero_recibo"),
                    rs.getTimestamp("fecha_donacion").toString(),
                    rs.getString("cedula")  // Si necesitas la cédula en los resultados, puedes incluirla en la consulta
                );
                donacionesList.add(donacion);
            }
        }

        return donacionesList;
    }
}
