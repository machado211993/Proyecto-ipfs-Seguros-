package com.ipfs.web.entidades;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Relevamiento {

    @Id
    private String idRelevamiento;
    
    private String agenciaSeguro;         
    
 
    @OneToOne
    private Conductor conductor;

    @OneToOne
    private Vehiculo vehiculo;

    @OneToOne
    private Siniestro siniestro;

    @OneToOne
    private Cliente cliente;

    public Relevamiento() {
    }

    public Relevamiento(String idRelevamiento, String agenciaSeguro, Conductor conductor, Vehiculo vehiculo, Siniestro siniestro, Cliente cliente) {
        this.idRelevamiento = idRelevamiento;
        this.agenciaSeguro = agenciaSeguro;
        this.conductor = conductor;
        this.vehiculo = vehiculo;
        this.siniestro = siniestro;
        this.cliente = cliente;
    }
    
}

