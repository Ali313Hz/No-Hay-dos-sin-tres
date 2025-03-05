package com.gestioninvitados.servicio;

import com.gestioninvitados.dao.InvitadoDAO;
import com.gestioninvitados.modelo.Invitado;
import com.gestioninvitados.util.ConexionBD;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase de servicio que implementa la lógica de negocio
 */
public class Servicio {
    private static final Logger LOGGER = Logger.getLogger(Servicio.class.getName());
    private InvitadoDAO invitadoDAO;

    /**
     * Constructor que inicializa el DAO con una conexión a la base de datos
     */
    public Servicio() {
        this.invitadoDAO = new InvitadoDAO(ConexionBD.obtenerConexion());
        LOGGER.log(Level.INFO, "Servicio inicializado");
    }

    /**
     * Obtiene todos los invitados
     * @return Lista de invitados
     */
    public List<Invitado> obtenerTodosInvitados() {
        LOGGER.log(Level.INFO, "Solicitando lista de todos los invitados");
        return invitadoDAO.listarTodos();
    }

    /**
     * Agrega un nuevo invitado
     * @param invitado Invitado a agregar
     * @return true si la operación fue exitosa, false en caso contrario
     */
    public boolean agregarInvitado(Invitado invitado) {
        LOGGER.log(Level.INFO, "Agregando nuevo invitado: {0}", invitado.getNombre());

        // Validación simple
        if (invitado.getNombre() == null || invitado.getNombre().trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Intento de agregar invitado con nombre vacío");
            return false;
        }

        return invitadoDAO.agregar(invitado);
    }

    /**
     * Elimina un invitado por su ID
     * @param id ID del invitado a eliminar
     * @return true si la operación fue exitosa, false en caso contrario
     */
    public boolean eliminarInvitado(int id) {
        LOGGER.log(Level.INFO, "Eliminando invitado con ID: {0}", id);
        return invitadoDAO.eliminar(id);
    }

    /**
     * Busca un invitado por su ID
     * @param id ID del invitado a buscar
     * @return Objeto Invitado encontrado o null si no existe
     */
    public Invitado buscarInvitadoPorId(int id) {
        LOGGER.log(Level.INFO, "Buscando invitado con ID: {0}", id);
        return invitadoDAO.buscarPorId(id);
    }
}