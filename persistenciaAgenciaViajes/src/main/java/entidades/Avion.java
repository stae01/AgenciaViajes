/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Chris
 */
@Entity
@Table(name = "Aviones")
public class Avion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAvion")
    private Long id;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "capacidad")
    private int capacidad;

    @Column(name = "esOcupado")
    private boolean busy;

    @OneToMany(mappedBy = "avion")
    private List<Vuelo> vuelos;

    public Avion() {
    }

    public Avion(Long id, String modelo, String matricula, int capacidad, boolean busy, List<Vuelo> vuelos) {
        this.id = id;
        this.modelo = modelo;
        this.matricula = matricula;
        this.capacidad = capacidad;
        this.busy = busy;
        this.vuelos = vuelos;
    }

    public Avion(String modelo, String matricula, int capacidad, boolean busy, List<Vuelo> vuelos) {
        this.modelo = modelo;
        this.matricula = matricula;
        this.capacidad = capacidad;
        this.busy = busy;
        this.vuelos = vuelos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setIsBusy(boolean busy) {
        this.busy = busy;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
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
        if (!(object instanceof Avion)) {
            return false;
        }
        Avion other = (Avion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Avion{");
        sb.append("id=").append(id);
        sb.append(", modelo=").append(modelo);
        sb.append(", matricula=").append(matricula);
        sb.append(", capacidad=").append(capacidad);
        sb.append(", vuelos=").append(vuelos);
        sb.append('}');
        return sb.toString();
    }

}
