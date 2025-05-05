<%-- 
    Document   : login
    Created on : 4 may 2025, 21:50:59
    Author     : pauli
--%>

<%@page import="objetos_negocio.ControlClientes"%>
<%@page import="objetos_negocio.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String accion = request.getParameter("accion");

    if ("login".equals(accion)) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Cliente clienteAutenticado = ControlClientes.autenticar(email, password);

        if (clienteAutenticado != null) {
            session.setAttribute("cliente", clienteAutenticado);
            if(clienteAutenticado.isIsAdmin()){
                response.sendRedirect("admin.jsp");
            }else{
                response.sendRedirect("index.jsp");
            }
            
            return;
        } else {
            request.setAttribute("errorLogin", "Correo o contraseña incorrectos");
        }
    }
%>

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
                <h2 class="menu-title">Iniciar Sesión</h2>

                <% if (request.getAttribute("errorLogin") != null) { %>
                    <p style="color: red;"><%= request.getAttribute("errorLogin") %></p>
                <% } %>

                <form action="login.jsp" method="POST">
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
                        <button type="button" class="menu-button btn-cancelar" onclick="window.location.href='index.jsp'">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
</body>
</html>