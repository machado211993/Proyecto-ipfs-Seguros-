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
public class Cliente {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idCliente;
    private String genero;
    private String apellidoNombre;
    private String relacion;
    private String dni;
    private String tel;
    private String cp;
    private String domicilio;
    private String localidad;
    private String provincia;
    private String estadoCivil;
    private String fechaNacimiento;

    public Cliente() {
    }

    public Cliente(String idCliente, String genero, String apellidoNombre, String relacion, String dni, String tel, String cp, String domicilio, String localidad, String provincia, String estadoCivil, String fechaNacimiento) {
        this.idCliente = idCliente;
        this.genero = genero;
        this.apellidoNombre = apellidoNombre;
        this.relacion = relacion;
        this.dni = dni;
        this.tel = tel;
        this.cp = cp;
        this.domicilio = domicilio;
        this.localidad = localidad;
        this.provincia = provincia;
        this.estadoCivil = estadoCivil;
        this.fechaNacimiento = fechaNacimiento;
    }

}
