/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Chris
 */
@Entity
@Table(name = "Vuelos")
public class Vuelo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVuelo")
    private Long id;

    @Column(name = "origen")
    private String origen;

    @Column(name = "destino")
    private String destino;

    @Column(name = "fechaSalida")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaSalida;

    @Column(name = "fechaLlegada")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaLlegada;

    @ManyToOne
    @JoinColumn(name = "idAvion")
    private Avion avion;

    @OneToMany(mappedBy = "vuelo")
    private List<Reserva> reservas;

    public Vuelo() {
    }

    public Vuelo(Long id, String origen, String destino, Date fechaSalida, Date fechaLlegada, Avion avion, List<Reserva> reservas) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.avion = avion;
        this.reservas = reservas;
    }

    public Vuelo(String origen, String destino, Date fechaSalida, Date fechaLlegada, Avion avion, List<Reserva> reservas) {
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.avion = avion;
        this.reservas = reservas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
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
        if (!(object instanceof Vuelo))
        {
            return false;
        }
        Vuelo other = (Vuelo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vuelo{");
        sb.append("id=").append(id);
        sb.append(", origen=").append(origen);
        sb.append(", destino=").append(destino);
        sb.append(", fechaSalida=").append(fechaSalida);
        sb.append(", fechaLlegada=").append(fechaLlegada);
        sb.append(", avion=").append(avion);
        sb.append(", reservas=").append(reservas);
        sb.append('}');
        return sb.toString();
    }

}
