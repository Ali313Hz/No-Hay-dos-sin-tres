<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Invitados</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Lista de Invitados</h1>
    </header>

    <nav>
        <ul>
            <li><a href="invitados?accion=listar">Ver Todos los Invitados</a></li>
            <li><a href="invitados?accion=mostrarFormularioAlta">Agregar Nuevo Invitado</a></li>
            <li><a href="invitados?accion=mostrarFormularioBaja">Eliminar Invitado</a></li>
            <li><a href="index.jsp">Inicio</a></li>
        </ul>
    </nav>

    <main>
        <c:if test="${not empty mensaje}">
            <div class="mensaje">
                <p>${mensaje}</p>
            </div>
        </c:if>

        <div class="tabla-container">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="invitado" items="${invitados}">
                    <tr>
                        <td>${invitado.id}</td>
                        <td>${invitado.nombre}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <c:if test="${empty invitados}">
                <p class="no-data">No hay invitados registrados.</p>
            </c:if>
        </div>
    </main>

    <footer>
        <p>&copy; 2025 Sistema de Gestión de Invitados</p>
    </footer>
</div>
</body>
</html>