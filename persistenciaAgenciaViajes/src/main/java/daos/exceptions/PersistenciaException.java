/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos.exceptions;

/**
 *
 * @author 
 */

public class PersistenciaException extends Exception {
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
    public PersistenciaException(String message) {
        super(message);
    }
}

