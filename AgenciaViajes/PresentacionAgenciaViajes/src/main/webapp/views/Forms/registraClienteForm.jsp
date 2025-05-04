<%-- 
    Document   : registraClienteForm
    Created on : 4 may 2025, 04:47:03
    Author     : Chris
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registra cliente - Good Riddance</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formulario.css?v=<%= System.currentTimeMillis()%>">
        <script src="${pageContext.request.contextPath}/scripts/validaRegistraCliente.js"></script>
    </head>
    <body>
        <header class="header">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo Good Riddance" class="logo">
        </header>    

        <main class="main-content">
            <div class="content-container">
                <div class="image-panel">
                    <img src="${pageContext.request.contextPath}/images/imagenFormularioCliente.jpeg" alt="Imagen de viaje" class="travel-image">
                </div>

                <div class="form-panel">
                    <h2 class="menu-title">Registra Usuario</h2>
                    <div id="alertaCampos" class="alerta"></div>

                    <form id="formularioRegistraCliente" action="${pageContext.request.contextPath}/agregarClienteServlet" method="POST" onsubmit="mostrarConfirmacion(event)">
                        <div class="form-row">
                            <div class="form-group">
                                <label for="nombre">Nombre:</label>
                                <input class="form-input" type="text" id="nombre" name="nombre" placeholder="Ingrese su nombre">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input class="form-input" type="text" id="email" name="email" placeholder="Ingrese su correo electrónico">
                            </div>

                            <div class="form-group">
                                <label for="password">Contraseña:</label>
                                <input class="form-input" type="password" id="password" name="password" placeholder="Ingrese su contraseña">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="telefono">Teléfono:</label>
                            <input class="form-input" type="tel" id="telefono" name= "telefono"placeholder="Ingrese su número telefónico">
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="fecha">Fecha de nacimiento:</label>
                                <input class="form-input" type="date" id="fecha" name="fecha">
                            </div>
                        </div>

                        <div class="form-buttons">
                            <button type="submit" class="menu-button btn-guardar">Guardar</button>
                            <button type="button" class="menu-button btn-cancelar" onclick="window.location.href = '${pageContext.request.contextPath}.jsp'">Cancelar</button>
                        </div>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>
