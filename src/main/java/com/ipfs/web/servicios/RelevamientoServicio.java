package com.ipfs.web.servicios;

import com.ipfs.web.entidades.Cliente;
import com.ipfs.web.entidades.Conductor;
import com.ipfs.web.entidades.Relevamiento;
import com.ipfs.web.entidades.Siniestro;
import com.ipfs.web.entidades.Vehiculo;
import com.ipfs.web.excepciones.MiException;
import com.ipfs.web.repositorios.ClienteRepositorio;
import com.ipfs.web.repositorios.ConductorRepositorio;
import com.ipfs.web.repositorios.RelevamientoRepositorio;
import com.ipfs.web.repositorios.SiniestroRepositorio;
import com.ipfs.web.repositorios.VehiculoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelevamientoServicio {

    @Autowired
    private RelevamientoRepositorio relevamientoRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private SiniestroRepositorio siniestroRepositorio;
    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;
    @Autowired 
    private ConductorRepositorio conductorRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void crearRelevamiento(/*MultipartFile archivo,*/ String idRelevamiento, String agenciaSeguro, String idSiniestro, String idVehiculo,
    String idCliente, String idConductor) throws MiException {

        validar(idRelevamiento, agenciaSeguro, idCliente, idConductor, idSiniestro, idVehiculo);

        Optional<Siniestro> respuestaSiniestro = siniestroRepositorio.findById(idSiniestro);
        Optional<Vehiculo> respuestaVehiculo = vehiculoRepositorio.findById(idVehiculo);
        Optional<Cliente> respuestaCliente = clienteRepositorio.findById(idCliente);
        Optional<Conductor> respuestaConductor = conductorRepositorio.findById(idConductor);

        Siniestro siniestro = new Siniestro();
        Vehiculo vehiculo = new Vehiculo();
        Cliente cliente = new Cliente();
        Conductor conductor = new Conductor();

        if (respuestaSiniestro.isPresent()) {

            siniestro = respuestaSiniestro.get();
        }

        if (respuestaVehiculo.isPresent()) {

            vehiculo = respuestaVehiculo.get();
        }
        if (respuestaCliente.isPresent()) {

            cliente = respuestaCliente.get();
        }
        if (respuestaConductor.isPresent()) {

            conductor = respuestaConductor.get();
        }

        Relevamiento relevamiento = new Relevamiento();

        relevamiento.setAgenciaSeguro(agenciaSeguro);
        relevamiento.setCliente(cliente);
        relevamiento.setConductor(conductor);
        relevamiento.setSiniestro(siniestro);
        relevamiento.setVehiculo(vehiculo);

        /*Imagen imagen = imagenServicio.guardar(archivo);*/

        /*producto.setImagen(imagen);*/

        relevamientoRepositorio.save(relevamiento);
    }
    //funcionalidad para listado de productos

    public List<Relevamiento> listarRelevamiento() {

        List<Relevamiento> relevamientos = new ArrayList();

        relevamientos = relevamientoRepositorio.findAll();

        return relevamientos;
    }
    //FUNCIONALIDAD PARA FILTROS DE PRODUCTOS (busqueda)

    public List<Relevamiento> listAll(String palabraClave) {
        if (palabraClave != null) {
            return relevamientoRepositorio.findAll(palabraClave);
        }

        return relevamientoRepositorio.findAll();
    }

//    //funcionalidad para paginacion 
//    public Page<Producto> findAll(Pageable pageable) {
//        return productoRepositorio.findAll(pageable);
//    }

    @Transactional
    public void modificarRelevamiento(/*MultipartFile archivo, */String idRelevamiento ,String agenciaSeguro , String idCliente, String idConductor, String idSiniestro, String idVehiculo) throws MiException {

        validar(idRelevamiento, agenciaSeguro, idCliente, idConductor, idSiniestro, idVehiculo);
        Optional<Relevamiento> respuesta = relevamientoRepositorio.findById(idRelevamiento);
        Optional<Siniestro> respuestaSiniestro = siniestroRepositorio.findById(idSiniestro);
        Optional<Vehiculo> respuestaVehiculo = vehiculoRepositorio.findById(idVehiculo);
        Optional<Cliente> respuestaCliente = clienteRepositorio.findById(idCliente);
        Optional<Conductor> respuestaConductor = conductorRepositorio.findById(idConductor);

        Siniestro siniestro = new Siniestro();
        Vehiculo vehiculo = new Vehiculo();
        Cliente cliente = new Cliente();
        Conductor conductor = new Conductor();


        if (respuestaSiniestro.isPresent()) {

            siniestro = respuestaSiniestro.get();
        }

        if (respuestaVehiculo.isPresent()) {

            vehiculo = respuestaVehiculo.get();
        }
        if (respuestaCliente.isPresent()) {

            cliente = respuestaCliente.get();
        }
        if (respuestaConductor.isPresent()) {

            conductor = respuestaConductor.get();
        }

        if (respuesta.isPresent()) {

            Relevamiento relevamiento = respuesta.get();

            relevamiento.setAgenciaSeguro(agenciaSeguro);
            relevamiento.setCliente(cliente);
            relevamiento.setConductor(conductor);
            relevamiento.setSiniestro(siniestro);
            relevamiento.setVehiculo(vehiculo);

           /*  String idImagen = null;

            if (relevamiento.getImagen() != null) {
                idImagen = relevamiento.getImagen().getIdImagen();
            }

            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            relevamiento.setImagen(imagen);*/

            relevamientoRepositorio.save(relevamiento);

        }
    }

    public Relevamiento getOne(String idRelevamiento) {
        return relevamientoRepositorio.getOne(idRelevamiento); //encontrar uno 
    }

    //eleminar producto
    @Transactional
    public void borrarPorId(String idRelevamiento) {
        relevamientoRepositorio.deleteById(idRelevamiento);
    }

    private void validar(/*MultipartFile archivo,*/ String idRelevamiento, String agenciaSeguro, String idVehiculo, String idSiniestro, String idCLiente, String idConductor) throws MiException {

        if (idRelevamiento == null) {
            throw new MiException("el idRelevamiento no puede ser nulo"); //
        }
        if (agenciaSeguro == null) {
            throw new MiException("la agencia de seguro no puede ser nulo"); //
        }
        if (idCLiente.isEmpty() || idCLiente == null) {
            throw new MiException("el idCliente no puede ser nulo o estar vacio");
        }
        if (idConductor == null) {
            throw new MiException("el idCOnductor no puede ser nulo");
        }
        if (idSiniestro.isEmpty() || idSiniestro == null) {
            throw new MiException("el idSiniestro no puede ser nulo o estar vacio");
        }

        if (idVehiculo.isEmpty() || idVehiculo == null) {
            throw new MiException("El idVehiculo no puede ser nula o estar vacia");
        }
       
    }
}