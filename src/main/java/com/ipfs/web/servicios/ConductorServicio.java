package com.ipfs.web.servicios;

import com.ipfs.web.entidades.Conductor;
import com.ipfs.web.excepciones.MiException;
import com.ipfs.web.repositorios.ConductorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConductorServicio {

    @Autowired
    private ConductorRepositorio conductorRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void crearConductor(/*MultipartFile archivo*/ String genero, String titular, String nombreConductor, String dni, String profesion, String tel, String domicilio, String cp, String localidad, String provincia, String estadoCivil, String fechaNacimiento, String testAlcoholemia, String nroRegistro, String categoria, String Expedido, String vencimiento) throws MiException {

        validar( genero, titular, nombreConductor, dni, profesion, tel, domicilio, cp, localidad, provincia, estadoCivil, fechaNacimiento, testAlcoholemia, nroRegistro, categoria, Expedido, vencimiento);

        Conductor conductor = new Conductor();

        conductor.setNombreConductor(nombreConductor);
        conductor.setCategoria(categoria);
        conductor.setCp(cp);
        conductor.setDni(dni);
        conductor.setDomicilio(domicilio);
        conductor.setEstadoCivil(estadoCivil);
        conductor.setExpedido(Expedido);
        conductor.setFechaNacimiento(fechaNacimiento);
        conductor.setGenero(genero);
        conductor.setLocalidad(localidad);
        conductor.setNroRegistro(nroRegistro);
        conductor.setProfesion(profesion);
        conductor.setProvincia(provincia);
        conductor.setTel(tel);
        conductor.setTestAlcoholemia(testAlcoholemia);
        conductor.setTitular(titular);
        conductor.setVencimiento(vencimiento);

        /*oferta.setAltaOferta(new Date());*/

        /*Imagen imagen = imagenServicio.guardar(archivo);*/

        /*oferta.setImagen(imagen);*/
        conductorRepositorio.save(conductor);
    }

    public List<Conductor> listarConductor() {

        List<Conductor> conductores = new ArrayList();

        conductores = conductorRepositorio.findAll(); //encuentra todo (findAll)

        return conductores;
    }
    //FUNCIONALIDAD PARA FILTROS DE CONDUCTOR (busqueda)

    public List<Conductor> listAll(String palabraClave) {
        if (palabraClave != null) {
            return conductorRepositorio.findAll(palabraClave);
        }

        return conductorRepositorio.findAll();
    }

    @Transactional //se pasa el idOferta porq se necesita en modificarOferta
    public void modificarConductor(/*MultipartFile archivo*/String idConductor, String genero, String titular, String nombreConductor, String dni, String profesion, String tel, String domicilio, String cp, String localidad, String provincia, String estadoCivil, String fechaNacimiento, String testAlcoholemia, String nroRegistro, String categoria, String Expedido, String vencimiento) throws MiException {

       

        Optional<Conductor> respuesta = conductorRepositorio.findById(idConductor);
        if (respuesta.isPresent()) {
            Conductor conductor = respuesta.get();
            conductor.setNombreConductor(nombreConductor);
            conductor.setCategoria(categoria);
            conductor.setCp(cp);
            conductor.setDni(dni);
            conductor.setDomicilio(domicilio);
            conductor.setEstadoCivil(estadoCivil);
            conductor.setExpedido(Expedido);
            conductor.setFechaNacimiento(fechaNacimiento);
            conductor.setGenero(genero);
            conductor.setLocalidad(localidad);
            conductor.setNroRegistro(nroRegistro);
            conductor.setProfesion(profesion);
            conductor.setProvincia(provincia);
            conductor.setTel(tel);
            conductor.setTestAlcoholemia(testAlcoholemia);
            conductor.setTitular(titular);
            conductor.setVencimiento(vencimiento);

            /*String idImagen = null;  //se le asigna null ??

            if (oferta.getImagen() != null) {
                idImagen = oferta.getImagen().getIdImagen();
            }

            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            oferta.setImagen(imagen);*/
            conductorRepositorio.save(conductor);
        }
    }

    public Conductor getOne(String idConductor) {
        return conductorRepositorio.getOne(idConductor); //conseguir uno
    }

    @Transactional
    public void borrarPorId(String idConductor) { //eliminar por num de id
        conductorRepositorio.deleteById(idConductor);
    }

    private void validar(/*String idOferta, MultipartFile archivo, */ String genero, String titular, String apellidoNombre, String dni, String profesion, String tel, String domicilio, String cp, String localidad, String provincia, String estadoCivil, String fechaNacimiento, String testAlcoholemia, String nroRegistro, String categoria, String Expedido, String vencimiento) throws MiException {

       

        if (genero.isEmpty() || genero == null) {
            throw new MiException("El genero no puede ser nulo o estar vacio");
        }
        if (titular.isEmpty() || titular == null) {
            throw new MiException("El titular no puede ser nulo o estar vacio");
        }

        if (apellidoNombre.isEmpty() || apellidoNombre == null) {
            throw new MiException("el apellido y nombre no puede ser nulo o estar vacio");
        }
        if (dni.isEmpty() || dni == null) {
            throw new MiException("el dni  no puede ser nulo o estar vacio");
        }
        if (profesion.isEmpty() || profesion == null) {
            throw new MiException("la profesion no puede ser nulo o estar vacio");
        }
        if (tel.isEmpty() || tel == null) {
            throw new MiException("el telefono no puede ser nulo o estar vacio");
        }
        if (domicilio.isEmpty() || domicilio == null) {
            throw new MiException("el domicilio no puede ser nulo o estar vacio");
        }
        if (cp.isEmpty() || cp == null) {
            throw new MiException("el codigo postal no puede ser nulo o estar vacio");
        }
        if (localidad.isEmpty() || localidad == null) {
            throw new MiException("la localidad no puede ser nulo o estar vacio");
        }
        if (provincia.isEmpty() || provincia == null) {
            throw new MiException("la provincia no puede ser nulo o estar vacio");
        }
        if (estadoCivil.isEmpty() || estadoCivil == null) {
            throw new MiException("el estado civil  no puede ser nulo o estar vacio");
        }
        if (fechaNacimiento.isEmpty() || fechaNacimiento == null) {
            throw new MiException("la fechaNacimiento  no puede ser nulo o estar vacio");
        }
        if (testAlcoholemia.isEmpty() || testAlcoholemia == null) {
            throw new MiException("el test de alcoholemia  no puede ser nulo o estar vacio");
        }
        if (nroRegistro.isEmpty() || nroRegistro == null) {
            throw new MiException("el numero de registro no puede ser nulo o estar vacio");
        }
        if (categoria.isEmpty() || categoria == null) {
            throw new MiException("la categoria no puede ser nulo o estar vacio");
        }
        if (Expedido.isEmpty() || Expedido == null) {
            throw new MiException("expedido no puede ser nulo o estar vacio");
        }
        if (vencimiento.isEmpty() || vencimiento == null) {
            throw new MiException("el  vencimiento no puede ser nulo o estar vacio");
        }
    }

}
