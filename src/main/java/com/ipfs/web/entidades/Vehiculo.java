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
public class Vehiculo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")

    private String idVehiculo;
    private String aseguradora;
    private String dominio;
    private String marca;
    private String modeloAuto;
    private String tipo;
    private String color;
    private String anio;
    private String danosMateriales;
    private String lesiones;
    private String muerte;
    
    public Vehiculo() {
    }

    public Vehiculo(String idVehiculo, String aseguradora, String dominio, String marca, String modeloAuto, String tipo, String color, String anio, String dañosMateriales, String lesiones, String muerte) {
        this.idVehiculo = idVehiculo;
        this.aseguradora = aseguradora;
        this.dominio = dominio;
        this.marca = marca;
        this.modeloAuto = modeloAuto;
        this.tipo = tipo;
        this.color = color;
        this.anio = anio;
        this.danosMateriales = danosMateriales;
        this.lesiones = lesiones;
        this.muerte = muerte;
    }



    


}
