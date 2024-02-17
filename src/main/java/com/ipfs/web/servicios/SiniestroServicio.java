package com.ipfs.web.servicios;

import com.ipfs.web.entidades.Siniestro;
import com.ipfs.web.excepciones.MiException;
import com.ipfs.web.repositorios.SiniestroRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiniestroServicio {

    @Autowired
    private SiniestroRepositorio siniestroRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void crearSiniestro(/*MultipartFile archivo*/String idSiniestro, String polizaNumero, String numeroSiniestro, String fechaSiniestro, String horaSiniestro, String lugarHecho, String estadoTiempo, String comisaria, String provincia) throws MiException {

        validar(idSiniestro, polizaNumero, numeroSiniestro, fechaSiniestro, horaSiniestro, lugarHecho, estadoTiempo, comisaria, provincia);

        Siniestro siniestro = new Siniestro();

        siniestro.setComisaria(comisaria);
        siniestro.setEstadoTiempo(estadoTiempo);
        siniestro.setFechaSiniestro(fechaSiniestro);
        siniestro.setHoraSiniestro(horaSiniestro);
        siniestro.setLugarHecho(lugarHecho);
        siniestro.setPolizaNumero(polizaNumero);
        siniestro.setProvincia(provincia);
        siniestro.setNumeroSiniestro(numeroSiniestro);

        /*oferta.setAltaOferta(new Date());*/

 /*Imagen imagen = imagenServicio.guardar(archivo);*/

 /*oferta.setImagen(imagen);*/
        siniestroRepositorio.save(siniestro);
    }

    public List<Siniestro> listarSiniestro() {

        List<Siniestro> siniestros = new ArrayList();

        siniestros = siniestroRepositorio.findAll(); //encuentra todo (findAll)

        return siniestros;
    }
    //FUNCIONALIDAD PARA FILTROS DE OFERTAS (busqueda)

    public List<Siniestro> listAll(String palabraClave) {
        if (palabraClave != null) {
            return siniestroRepositorio.findAll(palabraClave);
        }

        return siniestroRepositorio.findAll();
    }

    @Transactional //se pasa el idOferta porq se necesita en modificarOferta
    public void modificarSiniestro(/*MultipartFile archivo*/String idSiniestro, String polizaNumero, String numeroSiniestro, String fechaSiniestro, String horaSiniestro, String lugarHecho, String estadoTiempo, String comisaria, String provincia) throws MiException {

        validar(idSiniestro, polizaNumero, numeroSiniestro, fechaSiniestro, horaSiniestro, lugarHecho, estadoTiempo, comisaria, provincia);

        Optional<Siniestro> respuesta = siniestroRepositorio.findById(idSiniestro);
        if (respuesta.isPresent()) {
            Siniestro siniestro = respuesta.get();
            siniestro.setIdSiniestro(idSiniestro);
            siniestro.setPolizaNumero(polizaNumero);
            siniestro.setNumeroSiniestro(numeroSiniestro);
            siniestro.setFechaSiniestro(fechaSiniestro);
            siniestro.setHoraSiniestro(horaSiniestro);
            siniestro.setLugarHecho(lugarHecho);
            siniestro.setEstadoTiempo(estadoTiempo);
            siniestro.setComisaria(comisaria);
            siniestro.setProvincia(provincia);

            /*String idImagen = null;  //se le asigna null ??

            if (oferta.getImagen() != null) {
                idImagen = oferta.getImagen().getIdImagen();
            }

            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            oferta.setImagen(imagen);*/
            siniestroRepositorio.save(siniestro);
        }
    }

    public Siniestro getOne(String idSiniestro) {
        return siniestroRepositorio.getOne(idSiniestro); //conseguir uno
    }

    @Transactional
    public void borrarPorId(String idSiniestro) { //eliminar por num de id
        siniestroRepositorio.deleteById(idSiniestro);
    }

    private void validar(/*String idOferta, MultipartFile archivo, */String idSiniestro, String polizaNumero, String numeroSiniestro, String fechaSiniestro, String horaSiniestro, String lugarHecho, String estadoTiempo, String comisaria, String provincia) throws MiException {

        if (idSiniestro.isEmpty() || idSiniestro == null) {
            throw new MiException("El idSiniestro no puede ser nulo o estar vacio");
        }

        if (polizaNumero.isEmpty() || polizaNumero == null) {
            throw new MiException("El numero de poliza no puede ser nulo o estar vacio");
        }
        if (numeroSiniestro.isEmpty() || numeroSiniestro == null) {
            throw new MiException("El numero de siniestro no puede ser nulo o estar vacio");
        }

        if (fechaSiniestro.isEmpty() || fechaSiniestro == null) {
            throw new MiException("la fecha del siniestro no puede ser nulo o estar vacio");
        }
        if (horaSiniestro.isEmpty() || horaSiniestro == null) {
            throw new MiException("la hora del siniestro no puede ser nulo o estar vacio");
        }
        if (lugarHecho.isEmpty() || lugarHecho == null) {
            throw new MiException("el lugar del hecho no puede ser nulo o estar vacio");
        }
        if (estadoTiempo.isEmpty() || estadoTiempo == null) {
            throw new MiException("el estado de tiempo no puede ser nulo o estar vacio");
        }
        if (comisaria.isEmpty() || comisaria == null) {
            throw new MiException("la comisaria no puede ser nulo o estar vacio");
        }
        if (provincia.isEmpty() || provincia == null) {
            throw new MiException("la provincia no puede ser nulo o estar vacio");
        }

    }
}
