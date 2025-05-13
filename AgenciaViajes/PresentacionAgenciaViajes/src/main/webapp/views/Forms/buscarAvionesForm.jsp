<%-- 
    Document   : buscarAviones
    Created on : 13 may 2025, 13:05:46
    Author     : pauli
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Buscar Aviones</title>
    <link rel="stylesheet" href="styles/formulario.css">
</head>
<body>
   
    <div class="main-content">
        <div class="menu-container">
            <h1 class="menu-title">Buscar Aviones</h1>
            <form action="BuscarAvionesServlet" method="get">
                <div class="form-row">
                    <div class="form-group">
                        <label for="modelo">Modelo del avión</label>
                        <input type="text" name="modelo" id="modelo" class="form-input" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="capacidad">Capacidad mínima</label>
                        <input type="number" name="capacidad" id="capacidad" class="form-input" min="1">
                    </div>
                </div>
                <div class="form-buttons">
                    <button type="submit" class="menu-button">Buscar Aviones</button>
                </div>
            </form>
        </div>
    </div>

</body>
</html>