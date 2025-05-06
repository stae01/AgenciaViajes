<%-- 
    Document   : editarCliente.jsp
    Created on : 6 may 2025, 13:47:35
    Author     : pauli
--%>

<%@ page import="objetos_negocio.ControlClientes, objetos_negocio.Cliente" %>
<%@ page import="java.time.LocalDate" %>
<%
    String email = request.getParameter("email");
    String nombre = request.getParameter("nombre");
    String telefono = request.getParameter("telefono");
    String fecha = request.getParameter("fechaNacimiento");
    boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));

    LocalDate fechaNacimiento = (fecha != null && !fecha.isEmpty()) ? LocalDate.parse(fecha) : null;

    for (Cliente c : ControlClientes.obtener_lista()) {
        if (c.getEmail().equals(email)) {
            c.setNombre(nombre);
            c.setTelefono(telefono);
            c.setFechaNacimiento(fechaNacimiento);
            c.setIsAdmin(isAdmin);
            break;
        }
    }

    response.sendRedirect("admin.jsp");
%>