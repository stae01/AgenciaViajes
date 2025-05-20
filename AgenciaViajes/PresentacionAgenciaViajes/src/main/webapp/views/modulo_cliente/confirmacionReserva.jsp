<%-- 
    Document   : confirmacionReserva
    Created on : 20 may 2025, 5:21:17
    Author     : pauli
--%>

<%@page import="entidades.Reserva"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Confirmación de Reserva - Good Riddance</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formulario.css?v=<%= System.currentTimeMillis()%>">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>
        <%
            Reserva reserva = (Reserva) request.getAttribute("reserva");
            String mensaje = (String) request.getAttribute("mensaje");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            if (reserva == null) {
                response.sendRedirect(request.getContextPath() + "/consultarVuelosClienteServlet");
                return;
            }
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
                    <h2 class="menu-title"><%= mensaje != null ? mensaje : "Reserva confirmada"%></h2>

                    <p><strong>Cliente:</strong> <%= reserva.getCliente().getNombres()%></p>
                    <p><strong>Vuelo:</strong> <%= reserva.getVuelo().getOrigen()%> → <%= reserva.getVuelo().getDestino()%></p>
                    <p><strong>Fecha de salida:</strong> <%= sdf.format(reserva.getVuelo().getFechaSalida())%></p>
                    <p><strong>Fecha de llegada:</strong> <%= sdf.format(reserva.getVuelo().getFechaLlegada())%></p>
                    <p><strong>Cantidad de pasajeros:</strong> <%= reserva.getCantidadPasajeros()%></p>

                    <a href="${pageContext.request.contextPath}/consultarVuelosClienteServlet" class="menu-button btn-cancelar">Volver a Vuelos</a>
                </div>

            </div>
        </main>

    </body>
</html>