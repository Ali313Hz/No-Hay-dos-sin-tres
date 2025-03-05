<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión de Invitados</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Bienvenido al Sistema de Gestión de Invitados</h1>
    </header>

    <nav>
        <ul>
            <li><a href="invitados?accion=listar">Ver Todos los Invitados</a></li>
            <li><a href="invitados?accion=mostrarFormularioAlta">Agregar Nuevo Invitado</a></li>
            <li><a href="invitados?accion=mostrarFormularioBaja">Eliminar Invitado</a></li>
        </ul>
    </nav>

    <main>
        <div class="info-card">
            <h2>Gestione su evento con facilidad</h2>
            <p>Esta aplicación le permite administrar de manera sencilla la lista de invitados para su evento.</p>
            <p>Utilice el menú superior para navegar entre las diferentes funciones disponibles.</p>
        </div>
    </main>

    <footer>
        <p>&copy; 2025 Sistema de Gestión de Invitados</p>
    </footer>
</div>
</body>
</html>