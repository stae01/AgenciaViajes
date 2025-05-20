<%-- 
    Document   : consultaClientes
    Created on : 14 may 2025, 01:54:48
    Author     : Chris
--%>

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
                            <th>Acciones</th>
                        </tr>
                        <% int i = 0;
                            for (Cliente cliente : ControlClientes.obtener_lista()) {
                                i++;
                        %>
                        <tr id="fila<%=i%>">
                        <form action="editarCliente.jsp" method="post" id="form<%=i%>">
                            <!-- Campos deshabilitados por defecto -->
                            <td><input type="text" name="nombre" id="nombre<%=i%>" value="<%= cliente.getNombre()%>" readonly /></td>
                            <td><input type="text" name="email" id="email<%=i%>" value="<%= cliente.getEmail()%>" readonly /></td>
                            <td><input type="text" name="telefono" id="telefono<%=i%>" value="<%= cliente.getTelefono()%>" readonly /></td>
                            <td><input type="date" name="fechaNacimiento" id="fecha<%=i%>" value="<%= cliente.getFechaNacimiento() != null ? cliente.getFechaNacimiento() : ""%>" disabled /></td>
                            <td>
                                <select name="isAdmin" id="isAdmin<%=i%>" disabled>
                                    <option value="true" <%= cliente.isIsAdmin() ? "selected" : ""%>>Admin</option>
                                    <option value="false" <%= !cliente.isIsAdmin() ? "selected" : ""%>>Cliente</option>
                                </select>
                            </td>
                            <td>
                                <!-- Botón Editar con estilo -->
                                <button type="button" onclick="editarFila(<%=i%>)" id="btnEditar<%=i%>" class="menu-button btn-editar">✏️ Editar</button>

                                <!-- Botón Guardar oculto inicialmente con estilo -->
                                <input type="submit" value="💾 Guardar" id="btnGuardar<%=i%>" class="admin-btn-guardar" style="display:none;" />
                            </td>
                        </form>
                        <td>
                            <!-- Botón Eliminar con estilo -->
                            <form action="eliminarCliente.jsp" method="post">
                                <input type="hidden" name="email" value="<%= cliente.getEmail()%>" />
                                <input type="submit" value="🗑 Eliminar" class="btn-eliminar-pequeño" />
                            </form>
                        </td>
                        </tr>
                        <% }%>
                    </table>

                    <div class="form-buttons">
                        <button type="button" class="menu-button btn-registro" onclick="window.location.href = 'registraClienteForm.jsp'">
                            Agregar Cliente
                        </button>
                        <button type="button" class="menu-button btn-volver" onclick="window.location.href = '${pageContext.request.contextPath}/views/modulo_admin/menuAdmin.jsp'">
                            Volver
                        </button>
                    </div>
                </div>
            </div>
        </main>

        <script>
            // Función para habilitar los campos de la fila seleccionada
            function editarFila(id) {
                // Habilitar los campos de entrada
                document.getElementById('nombre' + id).readOnly = false;
                document.getElementById('telefono' + id).readOnly = false;
                document.getElementById('fecha' + id).disabled = false;
                document.getElementById('isAdmin' + id).disabled = false;

                // Mostrar el botón de guardar y ocultar el de editar
                document.getElementById('btnEditar' + id).style.display = 'none';
                document.getElementById('btnGuardar' + id).style.display = 'inline-block';
            }

            // Deshabilitar todos los campos al cargar la página
            window.onload = function () {
                const filas = document.querySelectorAll('tr[id^="fila"]');
                filas.forEach(function (fila) {
                    const id = fila.id.replace('fila', '');
                    // Deshabilitar campos
                    document.getElementById('nombre' + id).readOnly = true;
                    document.getElementById('telefono' + id).readOnly = true;
                    document.getElementById('fecha' + id).disabled = true;
                    document.getElementById('isAdmin' + id).disabled = true;
                });
            };
        </script>
    </body>
</html>