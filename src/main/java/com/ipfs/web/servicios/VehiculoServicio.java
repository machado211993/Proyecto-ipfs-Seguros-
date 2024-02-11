package com.ipfs.web.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ipfs.web.entidades.Vehiculo;
import com.ipfs.web.repositorios.VehiculoRepositorio;

@Service
public class VehiculoServicio {

    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    public List<Vehiculo> listarVehiculos(){
        return vehiculoRepositorio.findAll();
        
    }
    public void crearVehiculo(Vehiculo vehiculo){
        vehiculoRepositorio.save(vehiculo);
        
    } 
    public Vehiculo obtenerVehiculoPorId(String idVehiculo){
        return vehiculoRepositorio.findById(idVehiculo).get();
        
    }
    
    public void eliminarVehiculo(String idVehiculo){
        vehiculoRepositorio.deleteById(idVehiculo);
    }
}

