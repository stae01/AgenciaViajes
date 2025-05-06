<%-- 
    Document   : admin
    Created on : 5 may 2025, 0:17:53
    Author     : pauli
--%>

<%@page import="objetos_negocio.ControlClientes"%>
<%@page import="objetos_negocio.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Panel de Administración</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/formulario.css">
    </head>
    <body>
        <header class="header">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" class="logo">
        </header>

        <main class="main-content">
            <div class="content-container">
                <div class="form-panel">
                    <h2 class="menu-title">Clientes Registrados</h2>
                    
                    <table class="simple-table">
                        <tr>
                            <th>Nombre</th>
                            <th>Email</th>
                            <th>Teléfono</th>
                            <th>Nacimiento</th>
                            <th>Tipo</th>
                        </tr>
                        <% for (Cliente cliente : ControlClientes.obtener_lista()) { %>
                        <tr>
                            <td><%= cliente.getNombre() %></td>
                            <td><%= cliente.getEmail() %></td>
                            <td><%= cliente.getTelefono() %></td>
                            <td><%= cliente.getFechaNacimiento() != null ? cliente.getFechaNacimiento().toString() : "N/A" %></td>
                            <td><%= cliente.isIsAdmin() ? "Admin" : "Cliente" %></td>
                        </tr>
                        <% } %>
                    </table>
                    
                    <div class="form-buttons">
                        <button type="button" class="menu-button btn-registro" onclick="window.location.href='registraClienteForm.jsp'">
                            Agregar Cliente
                        </button>
                        <button type="button" class="menu-button btn-cancelar" onclick="window.location.href='login.jsp'">
                            Volver
                        </button>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>