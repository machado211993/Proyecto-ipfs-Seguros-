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
public class Siniestro {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")

    private String idSiniestro;
    private String polizaNumuero; 
    private String numeroSiniestro; 
    private String fechaSiniestro; 
    private String horaSiniestro; 
    private String lugarHecho; 
    private String estadoTiempo; 
    private String comisaria; 
    private String provincia; 

    public Siniestro() {
    }

    public Siniestro(String idSiniestro, String polizaNumuero, String numeroSiniestro, String fechaSiniestro, String horaSiniestro, String lugarHecho, String estadoTiempo, String comisaria, String provincia) {
        this.idSiniestro = idSiniestro;
        this.polizaNumuero = polizaNumuero;
        this.numeroSiniestro = numeroSiniestro;
        this.fechaSiniestro = fechaSiniestro;
        this.horaSiniestro = horaSiniestro;
        this.lugarHecho = lugarHecho;
        this.estadoTiempo = estadoTiempo;
        this.comisaria = comisaria;
        this.provincia = provincia;
    }
    
    
}
