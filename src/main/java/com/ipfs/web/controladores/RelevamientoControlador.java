package com.ipfs.web.controladores;

import com.ipfs.web.entidades.Relevamiento;
import com.ipfs.web.servicios.RelevamientoServicio;
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
@RequestMapping("/api/relevamiento")
public class RelevamientoControlador {

    @Autowired
    private RelevamientoServicio relevamientoServicio;

    @GetMapping("/listar")
    public List<Relevamiento> listarRelevamiento() {

        return relevamientoServicio.listarRelevamiento();
    }

    @GetMapping("/buscar/{idRelevamiento}")
    public ResponseEntity<Relevamiento> obtenerRelevamiento(@PathVariable String idRelevamiento) {
        try {
            Relevamiento relevamiento = relevamientoServicio.obtenerRelevamientoPorId(idRelevamiento);
            return new ResponseEntity<Relevamiento>(relevamiento, HttpStatus.OK);
        } catch (Exception MiException) {
            return new ResponseEntity<Relevamiento>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/registrar")
    public void guardarRelevamiento(@RequestBody Relevamiento relevamiento) {
        relevamientoServicio.crearRelevamiento(relevamiento);
    }

    @PutMapping("/modificar/{idRelevamiento}")
    public ResponseEntity<Relevamiento> actualizarRelevamiento(@RequestBody Relevamiento relevamiento, @PathVariable String idRelevamiento) {
        try {
            Relevamiento relevamientoExistente = relevamientoServicio.obtenerRelevamientoPorId(idRelevamiento);
            relevamientoServicio.crearRelevamiento(relevamiento);
            return new ResponseEntity<Relevamiento>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Relevamiento>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{idRelevamiento}")
    public void eliminarRelevamiento(@PathVariable String idRelevamiento) {
        relevamientoServicio.eliminarRelevamiento(idRelevamiento);
    }
}
