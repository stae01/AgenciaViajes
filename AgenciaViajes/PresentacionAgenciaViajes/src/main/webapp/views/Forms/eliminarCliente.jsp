<%-- 
    Document   : eliminarCliente
    Created on : 6 may 2025, 15:19:43
    Author     : pauli
--%>

<%@ page import="objetos_negocio.ControlClientes" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
    String email = request.getParameter("email");
    ControlClientes.eliminarCliente(email);
    response.sendRedirect("admin.jsp");
%>