package com.ipfs.web.controladores;

import com.ipfs.web.entidades.Conductor;
import com.ipfs.web.servicios.ConductorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conductor")
public class ConductorControlador {

    @Autowired
    private ConductorServicio conductorServicio;

    @GetMapping("/listar")
    public List<Conductor> listarConductor() {

        return conductorServicio.listarConductor();
    }

    @GetMapping("/{idConductor}")
    public ResponseEntity<Conductor> obtenerConductor(@PathVariable String idConductor) {
        try {
            Conductor conductor = conductorServicio.obtenerConductorPorId(idConductor);
            return new ResponseEntity<Conductor>(conductor, HttpStatus.OK);
        } catch (Exception MiException) {
            return new ResponseEntity<Conductor>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/registrar")
    public void guardarCliente(@RequestBody Conductor conductor) {
        conductorServicio.crearConductor(conductor);
    }

    @PutMapping("/modificar/{idConductor}")
    public ResponseEntity<Conductor> actualizarConductor(@RequestBody Conductor conductor, @PathVariable String idConductor) {
        try {
            Conductor conductorExistente = conductorServicio.obtenerConductorPorId(idConductor);
            conductorServicio.crearConductor(conductor);
            return new ResponseEntity<Conductor>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Conductor>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{idConductor}")
    public void eliminarConductor(@PathVariable String idConductor) {
        conductorServicio.eliminarConductor(idConductor);
    }
}
