package servletsVuelos;

import InterfacesFachada.VueloFachada;
import entidades.Vuelo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.VueloFachadaImpl;

public class ConsultarVuelosClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            VueloFachada vueloFachada = new VueloFachadaImpl();

            // Parámetros de paginación
            int pageSize = 10;
            int paginaActual = 1;

            // Verifica si se envió el número de página
            String paginaParam = request.getParameter("pagina");
            if (paginaParam != null && !paginaParam.isEmpty())
            {
                try
                {
                    paginaActual = Integer.parseInt(paginaParam);
                } catch (NumberFormatException e)
                {
                    paginaActual = 1;
                }
            }

            // Obtener total de productos y calcular total de páginas
            int totalVuelos = vueloFachada.contarVuelos();

            // Evitar que totalPaginas sea 0
            int totalPaginas = (int) Math.ceil((double) totalVuelos / pageSize);
            if (totalPaginas < 1)
            {
                totalPaginas = 1;
            }

            // Validar que la página esté dentro del rango
            if (paginaActual < 1)
            {
                paginaActual = 1;
            }
            if (paginaActual > totalPaginas)
            {
                paginaActual = totalPaginas;
            }

            // Obtener vuelos solo si hay alguno
            int primerResultado = (paginaActual - 1) * pageSize;
            List<Vuelo> vuelos = totalVuelos > 0
                    ? vueloFachada.consultarVuelos(pageSize, primerResultado)
                    : new ArrayList<>();

            // Debug logging
            System.out.println("Vuelos obtenidos: " + vuelos.size());
            for (Vuelo v : vuelos)
            {
                System.out.println("Vuelo: " + v.getId() + " - " + v.getOrigen() + " a " + v.getDestino());
            }

            // Enviar los datos a la vista
            request.setAttribute("vuelos", vuelos);
            request.setAttribute("paginaActual", paginaActual);
            request.setAttribute("totalPaginas", totalPaginas);

            // Forward to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/modulo cliente/consultaVuelosCliente.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e)
        {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar vuelos: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/modulo cliente/consultaVuelosCliente.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Simplemente redirige al método doGet
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para consultar vuelos para clientes";
    }
}
