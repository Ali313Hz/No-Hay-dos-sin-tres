<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Invitado</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Agregar Nuevo Invitado</h1>
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
        <div class="form-container">
            <form action="invitados" method="post">
                <input type="hidden" name="accion" value="alta">

                <div class="form-grupo">
                    <label for="nombre">Nombre del Invitado:</label>
                    <input type="text" id="nombre" name="nombre" required>
                </div>

                <div class="form-botones">
                    <button type="submit">Agregar Invitado</button>
                    <button type="reset">Limpiar</button>
                </div>
            </form>
        </div>
    </main>

    <footer>
        <p>&copy; 2025 Sistema de Gestión de Invitados</p>
    </footer>
</div>
</body>
</html>