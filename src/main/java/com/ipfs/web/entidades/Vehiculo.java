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
    private String modelo; 
    private String tipo; 
    private String color; 
    private String año; 
    private String dañosMateriales; 
    private String lesiones; 
    private String muerte; 
    @OneToOne
    public Imagen imagen;

    public Vehiculo(String idVehiculo, String aseguradora, String dominio, String marca, String modelo, String tipo, String color, String año, String dañosMateriales, String lesiones, String muerte, Imagen imagen) {
        this.idVehiculo = idVehiculo;
        this.aseguradora = aseguradora;
        this.dominio = dominio;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.color = color;
        this.año = año;
        this.dañosMateriales = dañosMateriales;
        this.lesiones = lesiones;
        this.muerte = muerte;
        this.imagen = imagen;
    }

    public Vehiculo() {
    }

    
    
}
