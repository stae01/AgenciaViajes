<%@page import="java.time.format.DateTimeParseException"%>
<%@page import="java.time.LocalDate"%>
<%@page import="objetos_negocio.ControlClientes"%>
<%@page import="objetos_negocio.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%
    boolean clienteAgregado = false;
    boolean fechaInvalida = false;

    String accion = request.getParameter("accion");

    if ("agregar".equals(accion)) {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telefono = request.getParameter("telefono");
        String fechaString = request.getParameter("fecha");

        try {
            LocalDate fechaNacimiento = LocalDate.parse(fechaString);
            Cliente cliente = new Cliente(nombre, email, password, telefono, fechaNacimiento);
            ControlClientes.agregar_cliente(cliente);
            clienteAgregado = true;
        } catch (DateTimeParseException e) {
            fechaInvalida = true;
        }
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registra cliente - Good Riddance</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formulario.css?v=<%= System.currentTimeMillis()%>">
        <script src="${pageContext.request.contextPath}/scripts/validaRegistraCliente.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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

                    <form id="formularioRegistraCliente" action="${pageContext.request.contextPath}/views/Forms/registraClienteForm.jsp" method="POST" onsubmit="mostrarConfirmacion(event)">
                        <input type="hidden" name="accion" value="agregar">
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
                            <input class="form-input" type="tel" id="telefono" name="telefono" placeholder="Ingrese su número telefónico">
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="fecha">Fecha de nacimiento:</label>
                                <input class="form-input" type="date" id="fecha" name="fecha">
                            </div>
                        </div>

                        <div class="form-buttons">
                            <button type="submit" class="menu-button btn-guardar">Guardar</button>
                            <button type="button" class="menu-button btn-cancelar" onclick="window.location.href = 'login.jsp'">Cancelar</button>                        
                        </div>
                    </form>
                </div>
            </div>
        </main>

        <% if (clienteAgregado) { %>
        <script>
            Swal.fire({
                icon: 'success',
                title: '¡Cliente registrado!',
                text: 'Redirigiendo al login...',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true
            }).then(() => {
                window.location.href = 'login.jsp';
            });
        </script>
        <% }%>

    </body>
</html>
