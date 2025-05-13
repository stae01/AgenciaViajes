/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.persistenciaagenciaviajes;

import conexion.Conexion;
import conexion.IConexion;
import daos.ClienteDAO;
import daos.exceptions.PersistenciaException;
import entidades.Cliente;

/**
 *
 * @author user
 */
public class PersistenciaAgenciaViajes {

    public static void main(String[] args) throws PersistenciaException {
        System.out.println("Hello World!");

        
        Conexion conexion = new Conexion();
        
        ClienteDAO clientedao = new ClienteDAO(conexion);
        
        
        Cliente cliente = new Cliente();
        
        clientedao.guardarCliente(cliente);
    }
}
