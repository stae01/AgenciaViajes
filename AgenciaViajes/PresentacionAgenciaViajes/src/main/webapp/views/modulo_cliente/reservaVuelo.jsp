<%-- 
    Document   : reservaVuelo
    Created on : May 19, 2025, 12:11:45 AM
    Author     : rramirez
--%>

<%@page import="entidades.Vuelo"%>
<%@page import="entidades.Cliente"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Reservar Vuelo - Good Riddance</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formulario.css?v=<%= System.currentTimeMillis()%>">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>
        <%
            Vuelo vuelo = (Vuelo) request.getAttribute("vuelo");
            Cliente cliente = (Cliente) session.getAttribute("cliente");
            if (vuelo == null || cliente == null) {
                response.sendRedirect(request.getContextPath() + "/consultarVuelosClienteServlet");
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        %>

        <header class="header">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo Good Riddance" class="logo">
            <div class="header-buttons">
                <button class="search-button" onclick="window.location.href = '${pageContext.request.contextPath}/manejaSesionServlet?accion=logout'">
                    <span class="button-icon">🔒</span> Cerrar Sesión
                </button>
            </div>
        </header>

        <main class="main-content">
            <div class="content-container">
                <div class="image-panel">
                    <img src="${pageContext.request.contextPath}/images/imagenFormularioCliente.jpeg" alt="Imagen de viaje" class="travel-image">
                </div>

                <div class="form-panel">
                    <h2 class="menu-title">Confirmar Reserva</h2>
                    <p><strong>Cliente:</strong> <%= cliente.getNombres()%></p>
                    <p><strong>Origen:</strong> <%= vuelo.getOrigen()%></p>
                    <p><strong>Destino:</strong> <%= vuelo.getDestino()%></p>
                    <p><strong>Salida:</strong> <%= sdf.format(vuelo.getFechaSalida())%></p>
                    <p><strong>Llegada:</strong> <%= sdf.format(vuelo.getFechaLlegada())%></p>

                    <form action="${pageContext.request.contextPath}/registrarReservaServlet" method="POST">
                        <input type="hidden" name="idVuelo" value="<%= vuelo.getId()%>">
                        <input type="hidden" name="idCliente" value="<%= cliente.getId()%>">

                        <div class="form-group">
                            <label for="cantidadPasajeros">Cantidad de pasajeros:</label>
                            <input type="number" id="cantidadPasajeros" name="cantidadPasajeros" min="1" max="10" value="1" required>
                        </div>

                        <div class="form-buttons">
                            <button type="submit" class="menu-button btn-guardar">Confirmar Reserva</button>
                            <a href="${pageContext.request.contextPath}/consultarVuelosClienteServlet" class="menu-button btn-cancelar">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </main>

    </body>
</html>