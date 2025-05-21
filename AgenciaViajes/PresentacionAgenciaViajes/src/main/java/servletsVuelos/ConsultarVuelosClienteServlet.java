package servletsVuelos;

import InterfacesFachada.VueloFachada;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entidades.Vuelo;
import entidades.VueloConImagen;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocioFachada.VueloFachadaImpl;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConsultarVuelosClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            VueloFachada vueloFachada = new VueloFachadaImpl();

            // Obtener listas para filtros (si quieres mostrarlos en la vista)
            List<String> origenesDisponibles = vueloFachada.obtenerOrígenesDisponibles();
            List<String> destinosDisponibles = vueloFachada.obtenerDestinosDisponibles();

            request.setAttribute("origenesDisponibles", origenesDisponibles);
            request.setAttribute("destinosDisponibles", destinosDisponibles);

            // Parámetros de filtro
            String origen = request.getParameter("origen");
            String destino = request.getParameter("destino");
            String fechaSalida = request.getParameter("fechaSalida");

            // Parámetros de paginación
            int pageSize = 10;
            int paginaActual = 1;
            String paginaParam = request.getParameter("pagina");
            if (paginaParam != null && !paginaParam.isEmpty()) {
                try {
                    paginaActual = Integer.parseInt(paginaParam);
                } catch (NumberFormatException e) {
                    paginaActual = 1;
                }
            }

            boolean hayFiltros = (origen != null && !origen.isEmpty())
                    || (destino != null && !destino.isEmpty())
                    || (fechaSalida != null && !fechaSalida.isEmpty());

            int totalVuelos;
            List<Vuelo> vuelos;
            int primerResultado = (paginaActual - 1) * pageSize;

            if (hayFiltros) {
                totalVuelos = vueloFachada.contarVuelosConFiltros(origen, destino, fechaSalida);
                vuelos = totalVuelos > 0
                        ? vueloFachada.consultarVuelosConFiltros(origen, destino, fechaSalida, pageSize, primerResultado)
                        : new ArrayList<>();
            } else {
                totalVuelos = vueloFachada.contarVuelos();
                vuelos = totalVuelos > 0
                        ? vueloFachada.consultarVuelos(pageSize, primerResultado)
                        : new ArrayList<>();
            }

            
            
            int totalPaginas = (int) Math.ceil((double) totalVuelos / pageSize);
            if (totalPaginas < 1) {
                totalPaginas = 1;
            }

            if (paginaActual < 1) {
                paginaActual = 1;
            }
            if (paginaActual > totalPaginas) {
                paginaActual = totalPaginas;
            }

            List<VueloConImagen> vuelosConImagen = new ArrayList<>();
            for (Vuelo vuelo : vuelos) {
                String urlImagen = obtenerImagenDestino(vuelo.getDestino());
                vuelosConImagen.add(new VueloConImagen(vuelo, urlImagen));
            }
            
            // Enviar atributos a la vista
            request.setAttribute("vuelos", vuelos);
            request.setAttribute("vuelosConImagen", vuelosConImagen);
            request.setAttribute("paginaActual", paginaActual);
            request.setAttribute("totalPaginas", totalPaginas);

            // Mantener filtros visibles en la vista
            request.setAttribute("origen", origen);
            request.setAttribute("destino", destino);
            request.setAttribute("fechaSalida", fechaSalida);

            // Forward a JSP cliente
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/modulo_cliente/consultaVuelosCliente.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar vuelos: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/modulo_cliente/consultaVuelosCliente.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    

    private String obtenerImagenDestino(String destino) {
        String PIXABAY_API_KEY = "50393076-cf2df96a4e23436ea5716b20f"; 
        String url = "https://pixabay.com/api/?key=" + PIXABAY_API_KEY
                   + "&q=" + destino + "&image_type=photo&per_page=1";

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray hits = jsonResponse.getJSONArray("hits");

                if (hits.length() > 0) {
                    return hits.getJSONObject(0).getString("webformatURL");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // Si hubo un error o no se encontró imagen
    }

    
    @Override
    public String getServletInfo() {
        return "Servlet para consultar vuelos para clientes con filtros y paginación.";
    }
}