package com.ipfs.web.servicios;

import com.ipfs.web.entidades.Relevamiento;
import com.ipfs.web.repositorios.RelevamientoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RelevamientoServicio {

    @Autowired
    private RelevamientoRepositorio relevamientoRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    public List<Relevamiento> listarRelevamiento() {
        return relevamientoRepositorio.findAll();

    }

    public void crearRelevamiento(Relevamiento relevamiento) {
        relevamientoRepositorio.save(relevamiento);

    }

    public Relevamiento obtenerRelevamientoPorId(String idRelevamiento) {
        return relevamientoRepositorio.findById(idRelevamiento).get();

    }

    public void eliminarRelevamiento(String idRelevamiento) {
        relevamientoRepositorio.deleteById(idRelevamiento);
    }
}
