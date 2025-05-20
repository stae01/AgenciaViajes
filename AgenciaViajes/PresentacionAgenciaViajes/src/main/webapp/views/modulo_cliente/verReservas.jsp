<%-- 
    Document   : verReservas
    Created on : 20 may 2025, 12:01:49
    Author     : pauli
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="entidades.Reserva"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Mis Reservas</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formulario.css?v=<%= System.currentTimeMillis()%>">
    </head>
    <body>

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
                <div class="form-panel">
                    <h2 class="menu-title">Mis Reservas</h2>

                    <%
                        List<Reserva> reservas = (List<Reserva>) request.getAttribute("reservas");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                        if (reservas == null || reservas.isEmpty()) {
                    %>
                    <p>No tienes reservas realizadas.</p>
                    <%
                    } else {
                        for (Reserva reserva : reservas) {
                    %>
                    <div class="reserva-card">
                        <p><strong>Vuelo:</strong> <%= reserva.getVuelo().getOrigen()%> → <%= reserva.getVuelo().getDestino()%></p>
                        <p><strong>Salida:</strong> <%= sdf.format(reserva.getVuelo().getFechaSalida())%></p>
                        <p><strong>Llegada:</strong> <%= sdf.format(reserva.getVuelo().getFechaLlegada())%></p>
                        <p><strong>Pasajeros:</strong> <%= reserva.getCantidadPasajeros()%></p>
                        <hr>
                    </div>
                    <%
                            }
                        }
                    %>

                    <a href="${pageContext.request.contextPath}/views/modulo_cliente/consultaVuelosCliente.jsp" class="menu-button">Volver</a>
                </div>

                <div class="image-panel">
                    <img src="${pageContext.request.contextPath}/images/imagenFormularioCliente.jpeg" alt="Imagen de viaje" class="travel-image">
                </div>
            </div>
        </main>

    </body>
</html>