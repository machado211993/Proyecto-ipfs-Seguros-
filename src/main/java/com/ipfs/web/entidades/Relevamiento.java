package com.ipfs.web.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
public class Relevamiento {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")

    private String idRelevamiento;
    private String agenciaSeguro;
    @ManyToOne
    private Siniestro siniestro;
    @ManyToOne
    private Vehiculo vehiculo;
    @ManyToOne
    private Cliente cliente; 
    @ManyToOne
    private Conductor conductor;
    
    public Relevamiento() {
    }

    public Relevamiento(String idRelevamiento, String agenciaSeguro, Siniestro siniestro, Vehiculo vehiculo,
            Cliente cliente, Conductor conductor) {
        this.idRelevamiento = idRelevamiento;
        this.agenciaSeguro = agenciaSeguro;
        this.siniestro = siniestro;
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.conductor = conductor;
    }


   
   
    
}
