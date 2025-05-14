<%-- 
    Document   : index
    Created on : 14 may 2025, 01:50:23
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login - Good Riddance</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formulario.css?v=<%= System.currentTimeMillis()%>">
    </head>
    <body>
        <header class="header">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" class="logo">
        </header>

        <main class="main-content">
            <div class="content-container">
                <div class="form-panel">
                    <%-- Verificar si se pasó el parámetro "logout" --%>
                    <%
                        String logout = request.getParameter("logout");
                        if ("true".equals(logout)) {
                    %>
                    <p style="color: green;">¡Has cerrado sesión exitosamente!</p>
                    <%
                        }
                    %>
                    <h2 class="menu-title">Iniciar Sesión</h2>

                    <%-- Mostrar mensaje de error si lo hay --%>
                    <%
                        String errorLogin = (String) request.getAttribute("errorLogin");
                        if (errorLogin != null) {
                    %>
                    <p style="color: red;"><%= errorLogin%></p>
                    <% }%>


                    <form action="${pageContext.request.contextPath}/manejaSesionServlet" method="POST">
                        <input type="hidden" name="accion" value="login">
                        <div class="form-group">
                            <label for="email">Correo:</label>
                            <input class="form-input" type="text" name="email" required placeholder="Correo electrónico">
                        </div>
                        <div class="form-group">
                            <label for="password">Contraseña:</label>
                            <input class="form-input" type="password" name="password" required placeholder="Contraseña">
                        </div>
                        <div class="form-buttons">
                            <button type="submit" class="menu-button btn-guardar">Entrar</button>
                            <button type="button" class="menu-button btn-registro" onclick="window.location.href = '${pageContext.request.contextPath}/views/modulo cliente/registraClienteForm.jsp'">Registrarse</button>
                        </div>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>