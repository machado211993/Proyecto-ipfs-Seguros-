package com.ipfs.web.controladores;

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

import com.ipfs.web.entidades.Vehiculo;
import com.ipfs.web.servicios.VehiculoServicio;

@RestController
@RequestMapping("/api")
public class VehiculoControlador {

    @Autowired
    private VehiculoServicio vehiculoServicio;

    @GetMapping("/vehiculos")
    public List<Vehiculo> listarVehiculos() {

        return vehiculoServicio.listarVehiculos();
    }

    @GetMapping("/vehiculo/{idVehiculo}")
    public ResponseEntity<Vehiculo> obtenerVehiculo(@PathVariable String idVehiculo) {
        try {
            Vehiculo vehiculo = vehiculoServicio.obtenerVehiculoPorId(idVehiculo);
            return new ResponseEntity<Vehiculo>(vehiculo, HttpStatus.OK);
        } catch (Exception MiException) {
            return new ResponseEntity<Vehiculo>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/vehiculo/registrar")
    public void guardarVehiculos(@RequestBody Vehiculo vehiculo) {
        vehiculoServicio.crearVehiculo(vehiculo);
    }

    @PutMapping("/vehiculo/modificar/{idVehiculo}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@RequestBody Vehiculo vehiculo, @PathVariable String idVehiculo) {
        try {
            Vehiculo vehiculoExistente = vehiculoServicio.obtenerVehiculoPorId(idVehiculo);
            vehiculoServicio.crearVehiculo(vehiculo);
            return new ResponseEntity<Vehiculo>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Vehiculo>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/vehiculo/eliminar/{idVehiculo}")
    public void eliminarVehiculo(@PathVariable String idVehiculo) {
        vehiculoServicio.eliminarVehiculo(idVehiculo);
    }
}
