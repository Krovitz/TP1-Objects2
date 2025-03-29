package opp2.persistencia;

import opp2.ConnectionManager;
import opp2.ejer1.RegistroInscripto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class EnDataBaseRegistroInscripto implements RegistroInscripto {
    @Override
    public void registro(LocalDate fechaInscripcion, int idParticipante, int idConcurso) {
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();

            statement = conn
                    .prepareStatement("INSERT INTO `inscripciones` (`FechaInscripcion`,`idParticipante`,`idConcurso`) " + "VALUES(?, ?, ?)");

            statement.setDate(1, Date.valueOf(fechaInscripcion));
            statement.setInt(2, idParticipante);
            statement.setInt(3, idConcurso);

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

