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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
    public void crearRelevamiento(/*MultipartFile archivo,*/ String idRelevamiento, String agenciaSeguro, String idSiniestro, String idVehiculo, String idCliente, String idConductor) throws MiException {

        validar(/*archivo,*/ idRelevamiento, agenciaSeguro, idSiniestro, idVehiculo, idCliente, idConductor);

        Optional<Cliente> respuestaCliente = clienteRepositorio.findById(idCliente);
        Optional<Vehiculo> respuestaVehiculo = vehiculoRepositorio.findById(idVehiculo);
        Optional<Siniestro> respuestaSiniestro = siniestroRepositorio.findById(idSiniestro);
        Optional<Conductor> respuestaConductor = conductorRepositorio.findById(idConductor);
     
        Siniestro datosSiniestro = new Siniestro();
        Vehiculo vehiculoAsegurado = new Vehiculo();
        Vehiculo vehiculoTercero = new Vehiculo();
        Cliente datosAsegurado = new Cliente();
        Conductor conductorVehiAsegurado = new Conductor();
        Cliente datosTitularTercero = new Cliente();
        Conductor conductorVehiTercero = new Conductor();
       

        if (respuestaSiniestro.isPresent()) {

            datosSiniestro = respuestaSiniestro.get();
        }

        if (respuestaVehiculo.isPresent()) {

            vehiculoAsegurado = respuestaVehiculo.get();
        }
        if (respuestaVehiculo.isPresent()) {

            vehiculoTercero = respuestaVehiculo.get();
        }
         if (respuestaCliente.isPresent()) {

            datosAsegurado = respuestaCliente.get();
        }
          if (respuestaConductor.isPresent()) {

            conductorVehiAsegurado = respuestaConductor.get();
        }
          if (respuestaCliente.isPresent()) {

            datosTitularTercero = respuestaCliente.get();
        }
          if (respuestaConductor.isPresent()) {

            conductorVehiTercero = respuestaConductor.get();
        }

        Relevamiento relevamiento = new Relevamiento();

        relevamiento.setAgenciaSeguro(agenciaSeguro);
        relevamiento.setDatosSiniestro(datosSiniestro);
        relevamiento.setVehiculoAsegurado(vehiculoAsegurado);
        relevamiento.setVehiculoTercero(vehiculoTercero);
        relevamiento.setDatosAsegurado(datosAsegurado);
        relevamiento.setConductorVehiAsegurado(conductorVehiAsegurado);
        relevamiento.setDatosTitularTercero(datosTitularTercero);
        relevamiento.setConductorVehiTercero(conductorVehiTercero);
        

        /*Imagen imagen = imagenServicio.guardar(archivo);*/

        /*producto.setImagen(imagen);*/

        relevamientoRepositorio.save(relevamiento);
    }
    //funcionalidad para listado de relevamiento

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

//    @Transactional
//    public void modificarProducto(MultipartFile archivo, String idProducto, String codigo, String nombre, Integer precio, String idProveedor, String idRubro) throws MiException {
//
//        validar(archivo, idProducto, codigo, nombre, precio, idProveedor, idRubro);
//
//        Optional<Producto> respuesta = productoRepositorio.findById(idProducto);
//        Optional<Proveedor> respuestaProveedor = proveedorRepositorio.findById(idProveedor);
//        Optional<Rubro> respuestaRubro = rubroRepositorio.findById(idRubro);
//
//        Proveedor proveedor = new Proveedor();
//        Rubro rubro = new Rubro();
//
//        if (respuestaProveedor.isPresent()) {
//
//            proveedor = respuestaProveedor.get();
//        }
//
//        if (respuestaRubro.isPresent()) {
//
//            rubro = respuestaRubro.get();
//        }
//
//        if (respuesta.isPresent()) {
//
//            Producto producto = respuesta.get();
//
//            producto.setCodigo(codigo);
//            producto.setNombre(nombre);
//
//            producto.setPrecio(precio);
//
//            producto.setProveedor(proveedor);
//
//            producto.setRubro(rubro);
//
//            String idImagen = null;
//
//            if (producto.getImagen() != null) {
//                idImagen = producto.getImagen().getIdImagen();
//            }
//
//            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
//            producto.setImagen(imagen);
//
//            productoRepositorio.save(producto);
//
//        }
//    }

    public Relevamiento getOne(String idRelevamiento) {
        return relevamientoRepositorio.getOne(idRelevamiento); //encontrar uno 
    }

    //eleminar producto
    @Transactional
    public void borrarPorId(String idRelevamiento) {
        relevamientoRepositorio.deleteById(idRelevamiento);
    }

    private void validar(/*MultipartFile archivo,*/ String idRelevamiento, String agenciaSeguro, String idSiniestro, String idVehiculo, String idCliente, String idConductor) throws MiException {

        if (idRelevamiento == null) {
            throw new MiException("el idRelevamiento no puede ser nulo"); //
        }
        if (agenciaSeguro == null) {
            throw new MiException("la agenciaSeguro no puede ser nulo"); //
        }
        if (idSiniestro.isEmpty() || idSiniestro == null) {
            throw new MiException("el siniestro no puede ser nulo o estar vacio");
        }
        if (idVehiculo == null) {
            throw new MiException("vehiculo no puede ser nulo");
        }
        if (idCliente.isEmpty() || idCliente == null) {
            throw new MiException("el cliente no puede ser nulo o estar vacio");
        }

        if (idConductor.isEmpty() || idConductor == null) {
            throw new MiException("El conductor no puede ser nula o estar vacia");
        }

    }
}