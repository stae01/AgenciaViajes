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
    <title>Reservar Vuelo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/reservaVuelo.css?v=<%= System.currentTimeMillis()%>">
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
        <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" class="logo">
        <button class="search-button" onclick="window.location.href = '${pageContext.request.contextPath}/manejaSesionServlet?accion=logout'">
            <span class="button-icon">🔒</span>
            Cerrar Sesión
        </button> 
    </header>

    <main class="main-content">
        <div class="container">
            <div class="reserva-card">
                <h1>Confirmar Reserva</h1>
                <p><strong>Cliente:</strong> <%= cliente.getNombres() %></p>
                <p><strong>Origen:</strong> <%= vuelo.getOrigen() %></p>
                <p><strong>Destino:</strong> <%= vuelo.getDestino() %></p>
                <p><strong>Salida:</strong> <%= sdf.format(vuelo.getFechaSalida()) %></p>
                <p><strong>Llegada:</strong> <%= sdf.format(vuelo.getFechaLlegada()) %></p>

                <form action="${pageContext.request.contextPath}/registrarReservaServlet" method="POST">
                    <input type="hidden" name="idVuelo" value="<%= vuelo.getId() %>">
                    <input type="hidden" name="idCliente" value="<%= cliente.getId() %>">

                    <div class="form-group">
                        <label for="asiento">Número de asiento:</label>
                        <input type="text" id="asiento" name="asiento" required placeholder="Ej. 12A">
                    </div>

                    <button type="submit" class="confirm-button">Confirmar Reserva</button>
                    <a href="${pageContext.request.contextPath}/consultarVuelosClienteServlet" class="cancel-button">Cancelar</a>
                </form>
            </div>
        </div>
    </main>
</body>
</html>
