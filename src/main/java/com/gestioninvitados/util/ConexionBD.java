package com.gestioninvitados.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase utilitaria para gestionar la conexión a la base de datos
 */
public class ConexionBD {
    private static final Logger LOGGER = Logger.getLogger(ConexionBD.class.getName());
    private static Connection conexion = null;

    /**
     * Obtiene una conexión a la base de datos
     * @return Objeto Connection con la conexión activa
     */
    public static Connection obtenerConexion() {
        if (conexion == null) {
            try {
                cargarDriver();

                Properties props = new Properties();
                InputStream input = ConexionBD.class.getClassLoader().getResourceAsStream("db.properties");

                if (input == null) {
                    LOGGER.log(Level.SEVERE, "No se pudo encontrar el archivo db.properties");
                    throw new RuntimeException("No se pudo encontrar el archivo db.properties");
                }

                props.load(input);

                String url = props.getProperty("db.url");
                String usuario = props.getProperty("db.usuario");
                String password = props.getProperty("db.password");

                conexion = DriverManager.getConnection(url, usuario, password);
                LOGGER.log(Level.INFO, "Conexión a base de datos establecida");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al conectar a la base de datos: {0}", e.getMessage());
                throw new RuntimeException("Error al conectar a la base de datos", e);
            }
        }
        return conexion;
    }

    /**
     * Carga el driver de MySQL
     */
    private static void cargarDriver() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        LOGGER.log(Level.INFO, "Driver MySQL cargado");
    }

    /**
     * Cierra la conexión a la base de datos
     */
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
                LOGGER.log(Level.INFO, "Conexión a base de datos cerrada");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al cerrar la conexión: {0}", e.getMessage());
            }
        }
    }
}