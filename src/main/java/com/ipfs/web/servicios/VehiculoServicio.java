package com.ipfs.web.servicios;

import com.ipfs.web.entidades.Imagen;
import com.ipfs.web.entidades.Vehiculo;
import com.ipfs.web.excepciones.MiException;
import com.ipfs.web.repositorios.VehiculoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VehiculoServicio {

    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void RegistrarVehiculo(String idVehiculo, MultipartFile archivo, String aseguradora, String dominio, String marca, String modelo, String tipo, String color, String año, String dañosMateriales, String lesiones, String muerte) throws MiException {

        validar(idVehiculo, archivo, aseguradora, dominio, marca, modelo, tipo, color, año, dañosMateriales, lesiones, muerte);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setAseguradora(aseguradora);
        vehiculo.setAño(año);
        vehiculo.setColor(color);
        vehiculo.setDañosMateriales(dañosMateriales);
        vehiculo.setDominio(dominio);
        vehiculo.setLesiones(lesiones);
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setMuerte(muerte);
        vehiculo.setTipo(tipo);

        Imagen imagen = imagenServicio.guardar(archivo);

        vehiculo.setImagen(imagen);

        vehiculoRepositorio.save(vehiculo);
    }

    public List<Vehiculo> listarVehiculos() {

        List<Vehiculo> vehiculos = new ArrayList();

        vehiculos = vehiculoRepositorio.findAll(); //encuentra todo (findAll)

        return vehiculos;
    }
    //FUNCIONALIDAD PARA FILTROS DE VEHICULOS (busqueda)

    public List<Vehiculo> listAll(String palabraClave) {
        if (palabraClave != null) {
            return vehiculoRepositorio.findAll(palabraClave);
        }

        return vehiculoRepositorio.findAll();
    }

    @Transactional //se pasa el idVehiculo porq se necesita en modificarOferta
    public void modificarOferta(String idVehiculo, MultipartFile archivo, String aseguradora, String dominio, String marca, String modelo, String tipo, String color, String año, String dañosMateriales, String lesiones, String muerte) throws MiException {

        validar(idVehiculo, archivo, aseguradora, dominio, marca, modelo, tipo, color, año, dañosMateriales, lesiones, muerte);

        Optional<Vehiculo> respuesta = vehiculoRepositorio.findById(idVehiculo);
        if (respuesta.isPresent()) {
            Vehiculo vehiculo = respuesta.get();

            vehiculo.setAseguradora(aseguradora);
            vehiculo.setAño(año);
            vehiculo.setColor(color);
            vehiculo.setDañosMateriales(dañosMateriales);
            vehiculo.setDominio(dominio);
            vehiculo.setLesiones(lesiones);
            vehiculo.setMarca(marca);
            vehiculo.setModelo(modelo);
            vehiculo.setMuerte(muerte);
            vehiculo.setTipo(tipo);

            String idImagen = null;  //se le asigna null ??

            if (vehiculo.getImagen() != null) {
                idImagen = vehiculo.getImagen().getIdImagen();
            }

            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            vehiculo.setImagen(imagen);

            vehiculoRepositorio.save(vehiculo);
        }
    }

    public Vehiculo getOne(String idVehiculo) {
        return vehiculoRepositorio.getOne(idVehiculo); //conseguir uno
    }

    @Transactional
    public void borrarPorId(String idVehiculo) { //eliminar por num de id
        vehiculoRepositorio.deleteById(idVehiculo);
    }

    private void validar(String idVehiculo, MultipartFile archivo, String aseguradora, String dominio, String marca, String modelo, String tipo, String color, String año, String dañosMateriales, String lesiones, String muerte) throws MiException {
        if (idVehiculo == null) {
            throw new MiException("el id no puede ser nulo"); //
        }
        if (idVehiculo.isEmpty() || idVehiculo == null) {
            throw new MiException("el id no puede ser nulo o estar vacio");
        }
        if (archivo == null) {
            throw new MiException("el archivo no puede ser nulo"); //
        }
        if (archivo.isEmpty() || archivo == null) {
            throw new MiException("el archivo no puede ser nulo o estar vacio");
        }

        if (aseguradora == null) {
            throw new MiException("la aseguradora no puede ser nulo"); //
        }
        if (aseguradora.isEmpty() || aseguradora == null) {
            throw new MiException("la aseguradora no puede ser nulo o estar vacio");

        }
        if (dominio == null) {
            throw new MiException("dominio no puede ser nulo");
        }
        if (dominio.isEmpty() || dominio == null) {
            throw new MiException("el dominio no puede ser nulo o estar vacio");
        }
        if (marca.isEmpty() || marca == null) {
            throw new MiException("la marca no puede ser nulo o estar vacio");
        }
        if (marca.isEmpty() || modelo == null) {
            throw new MiException("el modelo no puede ser nulo o estar vacio");
        }
        if (tipo.isEmpty() || tipo == null) {
            throw new MiException("el tipo no puede ser nulo o estar vacio");
        }
        if (color.isEmpty() || color == null) {
            throw new MiException("el color no puede ser nulo o estar vacio");
        }
        if (año.isEmpty() || año == null) {
            throw new MiException("el año no puede ser nulo o estar vacio");
        }
        if (dañosMateriales.isEmpty() || dañosMateriales == null) {
            throw new MiException("los daños materiales no puede ser nulo o estar vacio");
        }

        if (lesiones.isEmpty() || lesiones == null) {
            throw new MiException("lesiones no puede ser nulo o estar vacio");
        }
        if (muerte.isEmpty() || muerte == null) {
            throw new MiException("muerte no puede ser nulo o estar vacio");
        }

    }
}

