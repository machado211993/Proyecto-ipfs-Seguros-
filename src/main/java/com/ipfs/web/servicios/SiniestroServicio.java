package com.ipfs.web.servicios;

import com.ipfs.web.entidades.Siniestro;
import com.ipfs.web.repositorios.SiniestroRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SiniestroServicio {

    @Autowired
    private SiniestroRepositorio siniestroRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    public List<Siniestro> listarSiniestro() {
        return siniestroRepositorio.findAll();

    }

    public void crearSiniestro(Siniestro siniestro) {
        siniestroRepositorio.save(siniestro);

    }

    public Siniestro obtenerSiniestroPorId(String idSiniestro) {
        return siniestroRepositorio.findById(idSiniestro).get();

    }

    public void eliminarSiniestro(String idSiniestro) {
        siniestroRepositorio.deleteById(idSiniestro);
    }
}
