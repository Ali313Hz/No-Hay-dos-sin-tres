package com.gestioninvitados.dao;

import com.gestioninvitados.modelo.Invitado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para acceso a datos de los invitados
 */
public class InvitadoDAO {
    private static final Logger LOGGER = Logger.getLogger(InvitadoDAO.class.getName());
    private Connection conexion;

    /**
     * Constructor que recibe la conexión a la base de datos
     * @param conexion Objeto Connection activa
     */
    public InvitadoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Obtiene todos los invitados de la base de datos
     * @return Lista de invitados
     */
    public List<Invitado> listarTodos() {
        List<Invitado> invitados = new ArrayList<>();
        String sql = "SELECT id, nombre FROM invitados";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Invitado invitado = new Invitado();
                invitado.setId(rs.getInt("id"));
                invitado.setNombre(rs.getString("nombre"));
                invitados.add(invitado);
            }

            LOGGER.log(Level.INFO, "Se han recuperado {0} invitados", invitados.size());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar invitados: {0}", e.getMessage());
        }

        return invitados;
    }

    /**
     * Agrega un nuevo invitado a la base de datos
     * @param invitado Objeto Invitado a agregar
     * @return true si la operación fue exitosa, false en caso contrario
     */
    public boolean agregar(Invitado invitado) {
        String sql = "INSERT INTO invitados (nombre) VALUES (?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, invitado.getNombre());
            int filasAfectadas = stmt.executeUpdate();

            LOGGER.log(Level.INFO, "Invitado agregado: {0}, filas afectadas: {1}",
                    new Object[]{invitado.getNombre(), filasAfectadas});

            return filasAfectadas > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al agregar invitado: {0}", e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un invitado de la base de datos por su ID
     * @param id ID del invitado a eliminar
     * @return true si la operación fue exitosa, false en caso contrario
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM invitados WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();

            LOGGER.log(Level.INFO, "Invitado eliminado, ID: {0}, filas afectadas: {1}",
                    new Object[]{id, filasAfectadas});

            return filasAfectadas > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar invitado: {0}", e.getMessage());
            return false;
        }
    }

    /**
     * Busca un invitado por su ID
     * @param id ID del invitado a buscar
     * @return Objeto Invitado encontrado o null si no existe
     */
    public Invitado buscarPorId(int id) {
        String sql = "SELECT id, nombre FROM invitados WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Invitado invitado = new Invitado();
                    invitado.setId(rs.getInt("id"));
                    invitado.setNombre(rs.getString("nombre"));

                    LOGGER.log(Level.INFO, "Invitado encontrado, ID: {0}", id);
                    return invitado;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar invitado por ID: {0}", e.getMessage());
        }

        LOGGER.log(Level.INFO, "No se encontró invitado con ID: {0}", id);
        return null;
    }
}