package com.ipfs.web.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
    @OneToOne
    private Siniestro datosSiniestro;
    @OneToOne
    private Vehiculo vehiculoAsegurado;
    @OneToOne
    private Vehiculo vehiculoTercero; 
    @OneToOne
    private Cliente datosAsegurado; 
    @OneToOne
    private Conductor conductorVehiAsegurado;
    @OneToOne
    private Cliente datosTitularTercero; 
    @OneToOne
    private Conductor conductorVehiTercero; 

    public Relevamiento(String idRelevamiento, String agenciaSeguro, Siniestro datosSiniestro, Vehiculo vehiculoAsegurado, Vehiculo vehiculoTercero, Cliente datosAsegurado, Conductor conductorVehiAsegurado, Cliente datosTitularTercero, Conductor conductorVehiTercero) {
        this.idRelevamiento = idRelevamiento;
        this.agenciaSeguro = agenciaSeguro;
        this.datosSiniestro = datosSiniestro;
        this.vehiculoAsegurado = vehiculoAsegurado;
        this.vehiculoTercero = vehiculoTercero;
        this.datosAsegurado = datosAsegurado;
        this.conductorVehiAsegurado = conductorVehiAsegurado;
        this.datosTitularTercero = datosTitularTercero;
        this.conductorVehiTercero = conductorVehiTercero;
    }

    public Relevamiento() {
    }
   
    
}
