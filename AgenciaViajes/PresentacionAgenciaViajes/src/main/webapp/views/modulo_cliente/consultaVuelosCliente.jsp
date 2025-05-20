<%@ page import="entidades.Vuelo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Consulta de Vuelos</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/consultaVuelosCliente.css?v=<%= System.currentTimeMillis()%>" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css" />
        <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    </head>
    <body>
        <%
            // Redirigir si no hay vuelos cargados
            if (request.getAttribute("vuelos") == null) {
                response.sendRedirect(request.getContextPath() + "/consultarVuelosClienteServlet?pagina=1");
                return;
            }
        %>

        <header class="header">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" class="logo" />
            <<div class="header-buttons">
                <button class="search-button" onclick="window.location.href = '${pageContext.request.contextPath}/consultarReservasClienteServlet'">
                    <span class="button-icon">🧾</span> Mis Reservas
                </button>

                <button class="search-button" onclick="window.location.href = '${pageContext.request.contextPath}/manejaSesionServlet?accion=logout'">
                    <span class="button-icon"></span> Cerrar Sesión
                </button>
            </div>
        </header>

        <main class="main-content">
            <div class="container">
                <div class="content">
                    <div class="title-card">
                        <h1>Consulta de Vuelos</h1>
                    </div>

                    <form action="${pageContext.request.contextPath}/consultarVuelosClienteServlet" method="GET">
                        <div class="search-bar">

                            <select id="origen" name="origen" class="search-input">
                                <option value="">-- Seleccione origen --</option>
                                <%
                                    List<String> origenesDisponibles = (List<String>) request.getAttribute("origenesDisponibles");
                                    String origenSeleccionado = (String) request.getAttribute("origen");
                                    if (origenesDisponibles != null) {
                                        for (String origen : origenesDisponibles) {
                                %>
                                <option value="<%= origen%>" <%= origen.equals(origenSeleccionado) ? "selected" : ""%>><%= origen%></option>
                                <%
                                        }
                                    }
                                %>
                            </select>

                            <select id="destino" name="destino" class="search-input">
                                <option value="">-- Seleccione destino --</option>
                                <%
                                    List<String> destinosDisponibles = (List<String>) request.getAttribute("destinosDisponibles");
                                    String destinoSeleccionado = (String) request.getAttribute("destino");
                                    if (destinosDisponibles != null) {
                                        for (String destino : destinosDisponibles) {
                                %>
                                <option value="<%= destino%>" <%= destino.equals(destinoSeleccionado) ? "selected" : ""%>><%= destino%></option>
                                <%
                                        }
                                    }
                                %>
                            </select>

                            <input
                                id="fechaSalida"
                                name="fechaSalida"
                                type="text"
                                class="search-input"
                                placeholder="Fecha de salida"
                                value="<%= request.getAttribute("fechaSalida") != null ? request.getAttribute("fechaSalida") : ""%>"
                                autocomplete="off"
                                readonly
                                />

                            <button class="search-button" type="submit">Buscar</button>
                        </div>
                    </form>

                    <div class="cards-container">
                        <%
                            List<Vuelo> vuelos = (List<Vuelo>) request.getAttribute("vuelos");
                            if (vuelos != null && !vuelos.isEmpty()) {
                                for (Vuelo vuelo : vuelos) {
                        %>
                        <div class="card">
                            <div class="card-content">
                                <img src="${pageContext.request.contextPath}/images/Cancun.jpeg" alt="Vuelo" class="card-image" />
                                <h3>Origen: <%= vuelo.getOrigen()%></h3>
                                <h3>Destino: <%= vuelo.getDestino()%></h3>
                                <p>Salida: <%= vuelo.getFechaSalida()%></p>
                                <p>Llegada: <%= vuelo.getFechaLlegada()%></p>
                                <div class="card-actions">
                                    <form action="${pageContext.request.contextPath}/cargarVueloServlet" method="get">
                                        <input type="hidden" name="idVuelo" value="<%= vuelo.getId()%>" />
                                        <button type="submit" class="action-button search-button">
                                            <span class="button-icon">🎫</span> Reservar
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        } else {
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
                            int paginaActual = (request.getAttribute("paginaActual") != null) ? (Integer) request.getAttribute("paginaActual") : 1;
                            int totalPaginas = (request.getAttribute("totalPaginas") != null) ? (Integer) request.getAttribute("totalPaginas") : 1;

                            String origenParam = request.getAttribute("origen") != null ? (String) request.getAttribute("origen") : "";
                            String destinoParam = request.getAttribute("destino") != null ? (String) request.getAttribute("destino") : "";
                            String fechaParam = request.getAttribute("fechaSalida") != null ? (String) request.getAttribute("fechaSalida") : "";

                            // Codificar parámetros para URL
                            String origenEnc = java.net.URLEncoder.encode(origenParam, "UTF-8");
                            String destinoEnc = java.net.URLEncoder.encode(destinoParam, "UTF-8");
                            String fechaEnc = java.net.URLEncoder.encode(fechaParam, "UTF-8");

                            String baseURL = request.getContextPath() + "/consultarVuelosClienteServlet?origen=" + origenEnc
                                    + "&destino=" + destinoEnc + "&fechaSalida=" + fechaEnc + "&pagina=";

                            int maxVisiblePages = 5;
                        %>

                        <% if (paginaActual > 1) {%>
                        <a href="<%= baseURL + (paginaActual - 1)%>" class="page-button anterior">Anterior</a>
                        <% } %>

                        <%
                            // Mostrar primeras páginas si estamos lejos del inicio
                            if (paginaActual > maxVisiblePages / 2 + 1) {
                                for (int i = 1; i <= 2; i++) {
                        %>
                        <a href="<%= baseURL + i%>" class="page-button <%= (i == paginaActual) ? "active" : ""%>"><%= i%></a>
                        <%
                            }
                            if (paginaActual > 3) {
                        %>
                        <span class="page-button">...</span>
                        <%
                                }
                            }
                            // Páginas alrededor de la actual
                            for (int i = Math.max(1, paginaActual - 1); i <= Math.min(totalPaginas, paginaActual + 1); i++) {
                        %>
                        <a href="<%= baseURL + i%>" class="page-button <%= (i == paginaActual) ? "active" : ""%>"><%= i%></a>
                        <%
                            }
                            // Mostrar últimas páginas si estamos lejos del final
                            if (paginaActual < totalPaginas - maxVisiblePages / 2) {
                                if (paginaActual < totalPaginas - 2) {
                        %>
                        <span class="page-button">...</span>
                        <%
                            }
                            for (int i = totalPaginas - 1; i <= totalPaginas; i++) {
                        %>
                        <a href="<%= baseURL + i%>" class="page-button <%= (i == paginaActual) ? "active" : ""%>"><%= i%></a>
                        <%
                                }
                            }
                        %>

                        <% if (paginaActual < totalPaginas) {%>
                        <a href="<%= baseURL + (paginaActual + 1)%>" class="page-button siguiente">Siguiente</a>
                        <% }%>
                    </div>

                </div>
            </div>
        </main>
        <script>
            const origenSelect = document.getElementById("origen");
            const destinoSelect = document.getElementById("destino");
            const fechaInput = document.getElementById("fechaSalida");
            let calendar = null;

            function inicializarFlatpickr(fechasDisponibles) {
                if (calendar) {
                    calendar.destroy();
                }

                calendar = flatpickr(fechaInput, {
                    dateFormat: "Y-m-d",
                    enable: fechasDisponibles,
                    disableMobile: true,
                    allowInput: false,
                });
            }

            function cargarFechasDisponibles() {
                const origen = origenSelect.value;
                const destino = destinoSelect.value;

                if (!origen || !destino) {
                    fechaInput.value = '';
                    fechaInput.disabled = true;
                    if (calendar)
                        calendar.destroy();
                    return;
                }

                const baseContextPath = '<%= request.getContextPath()%>';
                const url = baseContextPath + "/FechasDisponiblesServlet?origen=" +
                        encodeURIComponent(origen) + "&destino=" + encodeURIComponent(destino);

                fetch(url)
                        .then(response => {
                            console.log(response);
                            if (!response.ok) {
                                throw new Error('Error en la respuesta del servidor');
                            }
                            return response.json();
                        })
                        .then(fechas => {
                            console.log(fechas);
                            if (!fechas || fechas.length === 0) {
                                fechaInput.value = '';
                                fechaInput.disabled = true;
                                alert('No hay fechas disponibles para la ruta seleccionada.');
                                if (calendar)
                                    calendar.destroy();
                                return;
                            }

                            fechaInput.disabled = false;
                            inicializarFlatpickr(fechas);

                            if (!fechas.includes(fechaInput.value)) {
                                fechaInput.value = '';
                            }
                        })
                        .catch(error => {
                            console.error('Error al cargar fechas:', error);
                            fechaInput.disabled = true;
                            if (calendar)
                                calendar.destroy();
                        });
            }

            origenSelect.addEventListener("change", cargarFechasDisponibles);
            destinoSelect.addEventListener("change", cargarFechasDisponibles);
        </script>
    </body>
</html>
