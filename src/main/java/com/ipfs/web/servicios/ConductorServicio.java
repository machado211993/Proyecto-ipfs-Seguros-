package com.ipfs.web.servicios;

import com.ipfs.web.entidades.Conductor;
import com.ipfs.web.repositorios.ConductorRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ConductorServicio {

    @Autowired
    private ConductorRepositorio conductorRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    public List<Conductor> listarConductor() {
        return conductorRepositorio.findAll();

    }

    public void crearConductor(Conductor conductor) {
        conductorRepositorio.save(conductor);

    }

    public Conductor obtenerConductorPorId(String idConductor) {
        return conductorRepositorio.findById(idConductor).get();

    }

    public void eliminarConductor(String idConductor) {
        conductorRepositorio.deleteById(idConductor);
    }
}
