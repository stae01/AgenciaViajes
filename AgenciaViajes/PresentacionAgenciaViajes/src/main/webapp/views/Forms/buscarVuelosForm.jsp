<%-- 
    Document   : buscarVuelos
    Created on : 11 may 2025, 11:01:31 p.m.
    Author     : user
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Buscar Vuelos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formulario.css?v=<%= System.currentTimeMillis() %>">
</head>
<body>

    <header class="header">
        <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo Good Riddance" class="logo">
    </header>

    <main class="main-content">
        <div class="content-container">
            <div class="image-panel">
                <img src="${pageContext.request.contextPath}/images/imagenFormularioVuelo.jpeg" alt="Imagen de vuelo" class="travel-image">
            </div>

            <div class="form-panel">
                <h2 class="menu-title">Buscar Vuelo</h2>

                <form action="${pageContext.request.contextPath}/BuscarVuelosServlet" method="get">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="origen">Origen</label>
                            <input type="text" name="origen" id="origen" class="form-input" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="destino">Destino</label>
                            <input type="text" name="destino" id="destino" class="form-input" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="fecha">Fecha</label>
                            <input type="date" name="fecha" id="fecha" class="form-input">
                        </div>
                    </div>
                    <div class="form-buttons">
                        <button type="submit" class="menu-button">Buscar Vuelos</button>
                    </div>
                </form>
            </div>
        </div>
    </main>

</body>
</html>
