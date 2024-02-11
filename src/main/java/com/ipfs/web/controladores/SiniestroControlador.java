package com.ipfs.web.controladores;

import com.ipfs.web.entidades.Siniestro;
import com.ipfs.web.servicios.SiniestroServicio;
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
@RequestMapping("/api/siniestro")
public class SiniestroControlador {

    @Autowired
    private SiniestroServicio siniestroServicio;

    @GetMapping("/listar")
    public List<Siniestro> listarSiniestro() {

        return siniestroServicio.listarSiniestro();
    }

    @GetMapping("/buscar/{idSiniestro}")
    public ResponseEntity<Siniestro> obtenerSiniestro(@PathVariable String idSiniestro) {
        try {
            Siniestro siniestro = siniestroServicio.obtenerSiniestroPorId(idSiniestro);
            return new ResponseEntity<Siniestro>(siniestro, HttpStatus.OK);
        } catch (Exception MiException) {
            return new ResponseEntity<Siniestro>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/registrar")
    public void guardarSiniestro(@RequestBody Siniestro siniestro) {
        siniestroServicio.crearSiniestro(siniestro);
    }

    @PutMapping("/modificar/{idSiniestro}")
    public ResponseEntity<Siniestro> actualizarSiniestro(@RequestBody Siniestro siniestro, @PathVariable String idSiniestro) {
        try {
            Siniestro siniestroExistente = siniestroServicio.obtenerSiniestroPorId(idSiniestro);
            siniestroServicio.crearSiniestro(siniestro);
            return new ResponseEntity<Siniestro>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Siniestro>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{idSiniestro}")
    public void eliminarSiniestro(@PathVariable String idSiniestro) {
        siniestroServicio.eliminarSiniestro(idSiniestro);
    }
}
