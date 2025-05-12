/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Chris
 */
@Entity
@Table(name = "Reservas")
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReserva")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idVuelo")
    private Vuelo vuelo;

    @Column(name = "cantidadPasajeros")
    private int cantidadPasajeros;

    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
    private Pago pago;

    public Reserva() {
    }

    public Reserva(Long id, Cliente cliente, Vuelo vuelo, int cantidadPasajeros, Pago pago) {
        this.id = id;
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.cantidadPasajeros = cantidadPasajeros;
        this.pago = pago;
    }

    public Reserva(Cliente cliente, Vuelo vuelo, int cantidadPasajeros, Pago pago) {
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.cantidadPasajeros = cantidadPasajeros;
        this.pago = pago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva))
        {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reserva{");
        sb.append("id=").append(id);
        sb.append(", cliente=").append(cliente);
        sb.append(", vuelo=").append(vuelo);
        sb.append(", cantidadPasajeros=").append(cantidadPasajeros);
        sb.append(", pago=").append(pago);
        sb.append('}');
        return sb.toString();
    }

}
