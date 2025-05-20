/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletManejaSesion;

import InterfacesFachada.ClienteFachada;
import entidades.Cliente;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import negocioFachada.ClienteFachadaImpl;

/**
 *
 * @author Chris
 */
public class ManejaSesionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();

        ClienteFachada clienteFachada = new ClienteFachadaImpl();
        // Crear admin solo si no existe
        try
        {
            Cliente admin = clienteFachada.buscarPorEmail("admin");
            if (admin == null)
            {
                Cliente nuevoAdmin = new Cliente();
                nuevoAdmin.setNombres("Administrador");
                nuevoAdmin.setEmail("admin");
                nuevoAdmin.setPassword("admin123");
                nuevoAdmin.setIsAdmin(true);
                clienteFachada.guardarCliente(nuevoAdmin);
            }
        } catch (Exception e)
        {
            log("Error al crear el usuario administrador: ", e);
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("logout".equals(accion))
        {
            HttpSession session = request.getSession(false);
            if (session != null)
            {
                session.invalidate(); // Cierra la sesión
            }
            response.sendRedirect("index.jsp?logout=true");
            return; // DETIENE la ejecución, evita otro sendRedirect
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        ClienteFachada clienteFachada = new ClienteFachadaImpl();

        if ("login".equals(accion))
        {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Cliente clienteAutenticado = clienteFachada.autenticarCliente(email, password);

            if (clienteAutenticado != null)
            {
                HttpSession session = request.getSession();
                session.setAttribute("cliente", clienteAutenticado);

                if (Boolean.TRUE.equals(clienteAutenticado.isIsAdmin()))
                {
                    response.sendRedirect(request.getContextPath() + "/views/modulo_admin/menuAdmin.jsp?exito=true");
                } else
                {
                    response.sendRedirect(request.getContextPath() + "/views/modulo_cliente/consultaVuelosCliente.jsp?exito=true");
                }

            } else
            {
                request.setAttribute("errorLogin", "Correo o contraseña incorrectos");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
