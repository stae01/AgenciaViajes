/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Chris
 */
@Entity
@Table (name = "Pagos")
public class Pago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPago")
    private Long id;

    @Column(name = "monto")
    private double monto;

    @Column(name = "fechaPago")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaPago;

    @OneToOne
    @JoinColumn(name = "idReserva")
    private Reserva reserva;

    public Pago() {
    }

    public Pago(Long id, double monto, Date fechaPago, Reserva reserva) {
        this.id = id;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.reserva = reserva;
    }

    public Pago(double monto, Date fechaPago, Reserva reserva) {
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.reserva = reserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
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
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pago{");
        sb.append("id=").append(id);
        sb.append(", monto=").append(monto);
        sb.append(", fechaPago=").append(fechaPago);
        sb.append(", reserva=").append(reserva);
        sb.append('}');
        return sb.toString();
    }

}
