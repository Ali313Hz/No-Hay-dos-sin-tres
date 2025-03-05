package com.gestioninvitados.servlet;

import com.gestioninvitados.modelo.Invitado;
import com.gestioninvitados.servicio.Servicio;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que maneja las peticiones relacionadas con los invitados
 */
@WebServlet("/invitados")
public class InvitadoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(InvitadoServlet.class.getName());

    private Servicio servicio;

    @Override
    public void init() throws ServletException {
        super.init();
        servicio = new Servicio();
        LOGGER.log(Level.INFO, "Servlet inicializado");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "listar";
        }

        LOGGER.log(Level.INFO, "Petición GET recibida. Acción: {0}", accion);
        procesarAccion(accion, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        LOGGER.log(Level.INFO, "Petición POST recibida. Acción: {0}", accion);
        procesarAccion(accion, request, response);
    }

    /**
     * Procesa la acción solicitada (listar, alta, baja)
     */
    private void procesarAccion(String accion, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (accion) {
            case "listar":
                listarInvitados(request, response);
                break;

            case "mostrarFormularioAlta":
                request.getRequestDispatcher("/agregarInvitado.jsp").forward(request, response);
                break;

            case "alta":
                altaInvitado(request, response);
                break;

            case "mostrarFormularioBaja":
                mostrarFormularioBaja(request, response);
                break;

            case "baja":
                bajaInvitado(request, response);
                break;

            default:
                LOGGER.log(Level.WARNING, "Acción desconocida: {0}", accion);
                response.sendRedirect(request.getContextPath() + "/invitados?accion=listar");
                break;
        }
    }

    /**
     * Lista todos los invitados
     */
    private void listarInvitados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Invitado> invitados = servicio.obtenerTodosInvitados();
        request.setAttribute("invitados", invitados);

        LOGGER.log(Level.INFO, "Listando {0} invitados", invitados.size());
        request.getRequestDispatcher("/listarInvitados.jsp").forward(request, response);
    }

    /**
     * Agrega un nuevo invitado
     */
    private void altaInvitado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        LOGGER.log(Level.INFO, "Solicitud de alta para invitado: {0}", nombre);

        Invitado invitado = new Invitado(nombre);
        boolean exito = servicio.agregarInvitado(invitado);

        if (exito) {
            request.setAttribute("mensaje", "Invitado agregado correctamente");
            LOGGER.log(Level.INFO, "Invitado agregado exitosamente: {0}", nombre);
        } else {
            request.setAttribute("mensaje", "No se pudo agregar al invitado");
            LOGGER.log(Level.WARNING, "Error al agregar invitado: {0}", nombre);
        }

        listarInvitados(request, response);
    }

    /**
     * Muestra el formulario de baja con la lista de invitados
     */
    private void mostrarFormularioBaja(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Invitado> invitados = servicio.obtenerTodosInvitados();
        request.setAttribute("invitados", invitados);

        LOGGER.log(Level.INFO, "Mostrando formulario de baja");
        request.getRequestDispatcher("/eliminarInvitado.jsp").forward(request, response);
    }

    /**
     * Elimina un invitado
     */
    private void bajaInvitado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        LOGGER.log(Level.INFO, "Solicitud de baja para invitado con ID: {0}", id);

        boolean exito = servicio.eliminarInvitado(id);

        if (exito) {
            request.setAttribute("mensaje", "Invitado eliminado correctamente");
            LOGGER.log(Level.INFO, "Invitado eliminado exitosamente, ID: {0}", id);
        } else {
            request.setAttribute("mensaje", "No se pudo eliminar al invitado");
            LOGGER.log(Level.WARNING, "Error al eliminar invitado con ID: {0}", id);
        }

        listarInvitados(request, response);
    }
}