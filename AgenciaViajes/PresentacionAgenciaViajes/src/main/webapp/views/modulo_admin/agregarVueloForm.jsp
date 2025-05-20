<%-- 
    Document   : agregarVueloForm
    Created on : 15 may 2025, 2:49:33 p.m.
    Author     : user
--%>
<%@page import="entidades.Avion"%>
<%@page import="java.util.List"%>
<%@page import="java.time.format.DateTimeParseException"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registrar Vuelo - Aerolínea</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formulario.css?v=<%= System.currentTimeMillis()%>">
        <script src="${pageContext.request.contextPath}/scripts/validaFormularioVuelo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>
        <header class="header">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo Good Riddance" class="logo">
        </header>

        <main class="main-content">
            <div class="content-container">
                <div class="image-panel">
                    <img src="${pageContext.request.contextPath}/images/imagenFormularioAvion.jpeg" alt="Imagen de vuelo" class="travel-image">
                </div>

                <div class="form-panel">
                    <h2 class="menu-title">Registrar Vuelo</h2>
                    <div id="alertaCampos" class="alerta"></div>

                    <form id="formularioRegistraVuelo" action="${pageContext.request.contextPath}/agregarVuelosServlet" method="POST" onsubmit="mostrarConfirmacion(event)">
                        <input type="hidden" name="accion" value="agregar">

                        <div class="form-row">
                            <div class="form-group">
                                <label for="origen">Origen:</label>
                                <input class="form-input" type="text" id="origen" name="origen" placeholder="Ingrese el origen del vuelo">
                            </div>

                            <div class="form-group">
                                <label for="destino">Destino:</label>
                                <input class="form-input" type="text" id="destino" name="destino" placeholder="Ingrese el destino del vuelo">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="fechaSalida">Fecha de Salida:</label>
                                <input class="form-input" type="datetime-local" id="fechaSalida" name="fechaSalida">
                            </div>

                            <div class="form-group">
                                <label for="horaSalida">Fecha de Llegada:</label>
                                <input class="form-input" type="datetime-local" id="fechaLlegada" name="fechaLlegada">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="avion">Avión:</label>
                                <select class="form-input" name="idAvion" required>
                                    <option value="" disabled selected>Selecciona un avión</option>
                                    <%
                                        List<Avion> aviones = (List<Avion>) request.getAttribute("aviones");
                                        if (aviones != null) {
                                            for (Avion avion : aviones) {
                                                if (!avion.isBusy()) { // Solo mostrar si NO está ocupado
                                    %>
                                    <option value="<%= avion.getId()%>">
                                        <%= avion.getModelo()%> - <%= avion.getMatricula()%>
                                    </option>
                                    <%
                                                }
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                        </div>

                        <div class="form-buttons">
                            <button type="submit" class="menu-button btn-guardar">Guardar</button>
                            <button type="button" class="menu-button btn-cancelar" onclick="window.location.href = '${pageContext.request.contextPath}/consultarVuelosServlet'">Cancelar</button>                        
                        </div>
                    </form>
                </div>
            </div>
        </main>

        <% if ("true".equals(request.getParameter("exito"))) { %>
        <script>
            Swal.fire({
            icon: 'success',
                    title: '¡Vuelo registrado!',
                    text: 'El vuelo se ha registrado correctamente.',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true
            }).then(() => {
            window.location.href = '${pageContext.request.contextPath}/consultarVuelosServlet';
            });
        </script>
        <% } %>

        <% if ("true".equals(request.getParameter("error"))) { %>
        <script>
            Swal.fire({
            icon: 'error',
                    title: 'Error',
                    text: 'No se pudo registrar el vuelo. Verifica los datos ingresados.',
                    showConfirmButton: true
            });
        </script>
        <% }%>
        <script>
            const inputDestino = document.getElementById('destino');
            const contenedorImagen = document.getElementById('imagenDestinoContainer');
            const PIXABAY_API_KEY = '50393076-cf2df96a4e23436ea5716b20f'; 

            async function buscarImagenDestino(destino) {
            if (!destino || destino.trim().length < 3) {
            contenedorImagen.innerHTML = '';
            return;
            }

            const url = "https://pixabay.com/api/?key=" + PIXABAY_API_KEY +
                    "&q=" + encodeURIComponent(destino) +
                    "&image_type=photo&per_page=1&safesearch=true";
            try {
            const response = await fetch(url);
            const data = await response.json();
            if (data.hits && data.hits.length > 0) {
            const imgUrl = data.hits[0].webformatURL;
            contenedorImagen.innerHTML = `<img src="${imgUrl}" alt="Imagen de ${destino}" style="max-width: 100%; height: auto; border-radius: 8px;">`;
            } else {
            contenedorImagen.innerHTML = `<p>No se encontró imagen para "${destino}".</p>`;
            }
            } catch (error) {
            console.error('Error al buscar imagen:', error);
            contenedorImagen.innerHTML = `<p>Error al buscar imagen.</p>`;
            }
            }

            // ⏱ Evita hacer muchas peticiones seguidas
            let timeoutId;
            inputDestino.addEventListener('input', () => {
            clearTimeout(timeoutId);
            timeoutId = setTimeout(() => {
            buscarImagenDestino(inputDestino.value);
            }, 700);
            });
        </script>

    </body>
</html>
