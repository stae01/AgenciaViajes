/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servletsAviones;

import servletsVuelos.*;
import InterfacesFachada.AvionFachada;
import entidades.Avion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import negocioFachada.AvionFachadaImpl;

/**
 *
 * @author pauli
 */
@WebServlet("/agregarAvionesServlet")
public class AgregarAvionesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AvionFachada avionFachada = new AvionFachadaImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String modelo = request.getParameter("modelo");
        String matricula = request.getParameter("matricula");
        int capacidad = Integer.parseInt(request.getParameter("capacidad"));

        // Creación de un nuevo objeto avión
        Avion avion = new Avion(modelo, matricula, capacidad, new ArrayList<>());

        boolean exito = false;
        try {
            // Llamar a la fachada para guardar el avión
            exito = avionFachada.guardarAvion(avion);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirigir según el resultado
        if (exito) {
            response.sendRedirect("agregarAvionesForm.jsp?exito=true");
        } else {
            response.sendRedirect("agregarAvionesForm.jsp?error=true");
        }
    }
}