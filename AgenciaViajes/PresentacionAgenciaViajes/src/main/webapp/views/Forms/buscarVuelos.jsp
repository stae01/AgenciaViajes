<%-- 
    Document   : buscarVuelos
    Created on : 11 may 2025, 11:01:31 p.m.
    Author     : user
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Buscar Vuelos</title>
    <link rel="stylesheet" href="styles/formulario.css">
</head>
<body>
    <jsp:include page="/includes/navbarCliente.jsp"/>

    <div class="main-content">
        <div class="menu-container">
            <h1 class="menu-title">Buscar Vuelos</h1>
            <form action="BuscarVuelosServlet" method="get">
                <div class="form-row">
                    <div class="form-group">
                        <label for="origen">Origen</label>
                        <input type="text" name="origen" id="origen" class="form-input" required>
                    </div>
                    <div class="form-group">
                        <label for="destino">Destino</label>
                        <input type="text" name="destino" id="destino" class="form-input" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="fechaSalida">Fecha de Salida</label>
                        <input type="date" name="fechaSalida" id="fechaSalida" class="form-input" required>
                    </div>
                    <div class="form-group">
                        <label for="fechaRegreso">Fecha de Regreso</label>
                        <input type="date" name="fechaRegreso" id="fechaRegreso" class="form-input">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="numPasajeros">Número de Pasajeros</label>
                        <input type="number" name="numPasajeros" id="numPasajeros" class="form-input" required min="1" max="10">
                    </div>
                </div>
                <div class="form-buttons">
                    <button type="submit" class="menu-button">Buscar Vuelos</button>
                </div>
            </form>
        </div>
    </div>

    <jsp:include page="/includes/footer.jsp"/>
</body>
</html>
