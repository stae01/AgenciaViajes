<%-- 
    Document   : agregarVueloForm
    Created on : 15 may 2025, 2:49:33 p.m.
    Author     : user
--%>
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
                    <img src="${pageContext.request.contextPath}/images/imagenFormularioVuelo.jpeg" alt="Imagen de vuelo" class="travel-image">
                </div>

                <div class="form-panel">
                    <h2 class="menu-title">Registrar Vuelo</h2>
                    <div id="alertaCampos" class="alerta"></div>

                    <form id="formularioRegistraVuelo" action="${pageContext.request.contextPath}/agregarVueloServlet" method="POST" onsubmit="mostrarConfirmacion(event)">
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
                                <input class="form-input" type="date" id="fechaSalida" name="fechaSalida">
                            </div>

                            <div class="form-group">
                                <label for="horaSalida">Hora de Salida:</label>
                                <input class="form-input" type="time" id="horaSalida" name="horaSalida">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="duracion">Duración:</label>
                                <input class="form-input" type="text" id="duracion" name="duracion" placeholder="Duración del vuelo">
                            </div>

                            <div class="form-group">
                                <label for="avionId">ID del Avión:</label>
                                <input class="form-input" type="text" id="avionId" name="avionId" placeholder="ID del avión asignado">
                            </div>
                        </div>

                        <div class="form-buttons">
                            <button type="submit" class="menu-button btn-guardar">Guardar</button>
                            <button type="button" class="menu-button btn-cancelar" onclick="window.location.href = 'index.jsp'">Cancelar</button>                        
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
                window.location.href = 'index.jsp';
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
        <% } %>
    </body>
</html>
