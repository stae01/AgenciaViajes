<%@page import="entidades.Vuelo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Consulta de Vuelos</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/consultaVuelosCliente.css?v=<%= System.currentTimeMillis()%>">
    </head>
    <body>
        <%
            // Redirect to servlet if vuelos attribute is not set
            if (request.getAttribute("vuelos") == null)
            {
                response.sendRedirect(request.getContextPath() + "/consultarVuelosClienteServlet?pagina=1");
                return;
            }
        %>
        <header class="header">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" class="logo">
            <button class="search-button" onclick="window.location.href = '${pageContext.request.contextPath}/manejaSesionServlet?accion=logout'">
                <span class="button-icon">📦</span>
                Cerrar Sesión
            </button> 
        </header>
        <main class="main-content">
            <div class="container">
                <div class="content">
                    <div class="title-card">
                        <h1>Consulta de Vuelos</h1>
                    </div>
                    <form action="${pageContext.request.contextPath}/busquedaVuelosServlet" method="GET">
                        <div class="search-bar">
                            <input id="busqueda" name="busqueda" type="text" class="search-input"
                                   placeholder="Buscar por nombre..."
                                   value="<%= request.getAttribute("busqueda") != null ? request.getAttribute("busqueda") : ""%>">
                            <button class="search-button">Buscar</button>
                        </div>
                    </form>
                    <div class="cards-container">
                        <%
                            List<Vuelo> vuelos = (List<Vuelo>) request.getAttribute("vuelos");
                            if (vuelos != null && !vuelos.isEmpty())
                            {
                                for (Vuelo vuelo : vuelos)
                                {
                        %>
                        <div class="card">
                            <div class="card-content">
                                <img src="${pageContext.request.contextPath}/images/Cancun.jpeg" alt="Vuelo" class="card-image">
                                <h3>Origen: <%= vuelo.getOrigen()%></h3>
                                <h3>Destino: <%= vuelo.getDestino()%></h3>
                                <p>Salida: <%= vuelo.getFechaSalida()%></p>
                                <p>Llegada: <%= vuelo.getFechaLlegada()%></p>
                                <div class="card-actions">
                                    <form action="${pageContext.request.contextPath}/reservaVuelo.jsp" method="GET" style="display:inline;">
                                        <input type="hidden" name="idVuelo" value="<%= vuelo.getId()%>" />
                                        <button type="submit" class="action-button search-button">
                                            <span class="button-icon">🎫</span>
                                            Reservar
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        } else
                        {
                        %>
                        <div class="no-results">
                            <p>No hay vuelos registrados.</p>
                        </div>
                        <%
                            }
                        %>
                    </div>
                    <div class="pagination">
                        <%
                            int paginaActual = (request.getAttribute("paginaActual") != null)
                                    ? (Integer) request.getAttribute("paginaActual") : 1;
                            int totalPaginas = (request.getAttribute("totalPaginas") != null)
                                    ? (Integer) request.getAttribute("totalPaginas") : 1;
                            String busquedaParam = request.getAttribute("busqueda") != null ? (String) request.getAttribute("busqueda") : "";
                            String baseURL = busquedaParam.isEmpty()
                                    ? request.getContextPath() + "/consultarVuelosClienteServlet?pagina="
                                    : request.getContextPath() + "/busquedaVuelosServlet?busqueda=" + java.net.URLEncoder.encode(busquedaParam, "UTF-8") + "&pagina=";
                            int maxVisiblePages = 5;
                        %>
                        <% if (paginaActual > 1)
                            {%>
                        <a href="<%= baseURL + (paginaActual - 1)%>" class="page-button anterior">Anterior</a>
                        <% } %>
                        <%
                            if (paginaActual > maxVisiblePages / 2 + 1)
                            {
                                for (int i = 1; i <= 2; i++)
                                {
                        %>
                        <a href="<%= baseURL + i%>" class="page-button <%= (i == paginaActual) ? "active" : ""%>"><%= i%></a>
                        <%
                            }
                            if (paginaActual > 3)
                            {
                        %>
                        <span class="page-button">...</span>
                        <%
                                }
                            }
                            for (int i = Math.max(1, paginaActual - 1); i <= Math.min(totalPaginas, paginaActual + 1); i++)
                            {
                        %>
                        <a href="<%= baseURL + i%>" class="page-button <%= (i == paginaActual) ? "active" : ""%>"><%= i%></a>
                        <%
                            }
                            if (paginaActual < totalPaginas - maxVisiblePages / 2)
                            {
                                if (paginaActual < totalPaginas - 2)
                                {
                        %>
                        <span class="page-button">...</span>
                        <%
                            }
                            for (int i = totalPaginas - 1; i <= totalPaginas; i++)
                            {
                        %>
                        <a href="<%= baseURL + i%>" class="page-button <%= (i == paginaActual) ? "active" : ""%>"><%= i%></a>
                        <%
                                }
                            }
                        %>
                        <% if (paginaActual < totalPaginas)
                            {%>
                        <a href="<%= baseURL + (paginaActual + 1)%>" class="page-button siguiente">Siguiente</a>
                        <% }%>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>