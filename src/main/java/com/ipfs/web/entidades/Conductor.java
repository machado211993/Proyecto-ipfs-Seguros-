package com.ipfs.web.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
public class Conductor {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idConductor;
    private String genero;
    private String titular;
    private String apellidoNombre;
    private String dni;
    private String profesion;
    private String tel;
    private String domicilio;
    private String cp;
    private String localidad;
    private String provincia;
    private String estadoCivil;
    private String fechaNacimiento;
    private String testAlcoholemia;
    private String nroRegistro;
    private String categoria;
    private String Expedido;
    private String vencimiento;

    public Conductor(String idConductor, String genero, String titular, String apellidoNombre, String dni, String profesion, String tel, String domicilio, String cp, String localidad, String provincia, String estadoCivil, String fechaNacimiento, String testAlcoholemia, String nroRegistro, String categoria, String Expedido, String vencimiento) {
        this.idConductor = idConductor;
        this.genero = genero;
        this.titular = titular;
        this.apellidoNombre = apellidoNombre;
        this.dni = dni;
        this.profesion = profesion;
        this.tel = tel;
        this.domicilio = domicilio;
        this.cp = cp;
        this.localidad = localidad;
        this.provincia = provincia;
        this.estadoCivil = estadoCivil;
        this.fechaNacimiento = fechaNacimiento;
        this.testAlcoholemia = testAlcoholemia;
        this.nroRegistro = nroRegistro;
        this.categoria = categoria;
        this.Expedido = Expedido;
        this.vencimiento = vencimiento;
    }

    public Conductor() {
    }

}
