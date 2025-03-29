package opp2.persistencia;

import opp2.ConnectionManager;
import opp2.restaurante.RegistroCosto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class EnDataBaseRegistroPagos implements RegistroCosto {
    @Override
    public void registro(LocalDate fecha, float costoTotal) {
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();

            statement = conn
                    .prepareStatement("INSERT INTO `pagos` (`fechaPago`,`costo`) " + "VALUES(?, ?)");

            statement.setDate(1, Date.valueOf(fecha));
            statement.setFloat(2, costoTotal);

            int cant = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir el error si ocurre alguno
        } finally {
            // Cerrar recursos en el bloque finally
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de posibles errores al cerrar
            }
        }
    }
}
