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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/menuAdmin.css">
    </head>
    <body>
        <header class="header">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" class="logo">
            <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/manejaSesionServlet?accion=logout'">
                <span class="button-icon">📦</span>
                Cerrar Sesión
            </button>
        </header>

        <main class="main-content">
            <div class="menu-container">
                <h2 class="menu-title">Menú Principal - Admin</h2>
                <div class="menu-grid">
                    <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/views/inventario/consultaVuelos.jsp'">
                        <span class="button-icon">📦</span>
                        Administrar vuelos
                    </button>
                    <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/views/modulo admin/consultaAviones.jsp'">
                        <span class="button-icon">📉</span>
                        Administrar Aviones
                    </button>
                    <button class="menu-button" onclick="window.location.href = '${pageContext.request.contextPath}/consultaClientesServlet'">
                        <span class="button-icon">🔧</span>
                        Administrar Clientes
                    </button>
                </div>
            </div>
        </main>

    </body>
</html>