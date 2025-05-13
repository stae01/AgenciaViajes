<%-- 
    Document   : agregarAvionesForm
    Created on : 13 may 2025, 14:11:48
    Author     : pauli
--%>

<%@page import="java.time.format.DateTimeParseException"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registrar Avión - Aerolínea</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formulario.css?v=<%= System.currentTimeMillis()%>">
        <script src="${pageContext.request.contextPath}/scripts/validaFormularioAvion.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>
        <header class="header">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo Aerolínea" class="logo">
        </header>

        <main class="main-content">
            <div class="content-container">
                <div class="image-panel">
                    <img src="${pageContext.request.contextPath}/images/imagenFormularioAvion.jpeg" alt="Imagen de avión" class="travel-image">
                </div>

                <div class="form-panel">
                    <h2 class="menu-title">Registrar Avión</h2>
                    <div id="alertaCampos" class="alerta"></div>

                    <form id="formularioRegistraAvion" action="${pageContext.request.contextPath}/agregarAvionesServlet" method="POST" onsubmit="mostrarConfirmacion(event)">
                        <input type="hidden" name="accion" value="agregar">

                        <div class="form-row">
                            <div class="form-group">
                                <label for="modelo">Modelo:</label>
                                <input class="form-input" type="text" id="modelo" name="modelo" placeholder="Ingrese el modelo del avión">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="matricula">Matrícula:</label>
                                <input class="form-input" type="text" id="matricula" name="matricula" placeholder="Ingrese la matrícula del avión">
                            </div>

                            <div class="form-group">
                                <label for="capacidad">Capacidad:</label>
                                <input class="form-input" type="number" id="capacidad" name="capacidad" placeholder="Ingrese la capacidad del avión">
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
                title: '¡Avión registrado!',
                text: 'El avión se ha registrado correctamente.',
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
                text: 'No se pudo registrar el avión. Verifica los datos ingresados.',
                showConfirmButton: true
            });
        </script>
        <% } %>
    </body>
</html>