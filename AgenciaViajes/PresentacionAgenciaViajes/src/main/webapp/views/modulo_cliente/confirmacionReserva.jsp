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

    // Determinar si el mensaje es de éxito
    boolean esExito = mensaje == null || (mensaje != null && mensaje.toLowerCase().contains("exitosamente"));
%>
<header class="header">
    <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo Good Riddance" class="logo">
    <button class="search-button" onclick="window.location.href = '${pageContext.request.contextPath}/manejaSesionServlet?accion=logout'">
        <span class="button-icon">🔒</span>
        Cerrar Sesión
    </button>
</header>

<main class="main-content">
    <div class="content-container">
        <div class="image-panel">
            <img src="${pageContext.request.contextPath}/images/imagenFormularioCliente.jpeg" alt="Imagen de viaje" class="travel-image">
        </div>

        <div class="form-panel">
            <% if (esExito) { %>
                <h2 class="menu-title">✅<%= mensaje != null ? mensaje : "Reserva confirmada" %></h2>
                <div class="alerta" id="alertaCampos"></div>
            <% } else { %>
                <h2 class="menu-title">Confirmación de Reserva</h2>
                <div class="alerta" id="alertaCampos" style="display: block;"><%= mensaje %></div>
            <% } %>

            <div class="form-row">
                <div class="form-group">
                    <p class="summary-item"><strong class="summary-label">Cliente:</strong> <span class="summary-value"><%= reserva.getCliente().getNombres() %></span></p>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <p class="summary-item"><strong class="summary-label">Vuelo:</strong> <span class="summary-value"><%= reserva.getVuelo().getOrigen() %> → <%= reserva.getVuelo().getDestino() %></span></p>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <p class="summary-item"><strong class="summary-label">Fecha de salida:</strong> <span class="summary-value"><%= sdf.format(reserva.getVuelo().getFechaSalida()) %></span></p>
                </div>
                <div class="form-group">
                    <p class="summary-item"><strong class="summary-label">Fecha de llegada:</strong> <span class="summary-value"><%= sdf.format(reserva.getVuelo().getFechaLlegada()) %></span></p>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <p class="summary-item"><strong class="summary-label">Cantidad de pasajeros:</strong> <span class="summary-value"><%= reserva.getCantidadPasajeros() %></span></p>
                </div>
            </div>

            <div class="form-buttons">
                <a href="${pageContext.request.contextPath}/consultarVuelosClienteServlet" class="menu-button btn-cancelar">Volver a Vuelos</a>
            </div>
        </div>
    </div>
</main>

    </body>
</html>
