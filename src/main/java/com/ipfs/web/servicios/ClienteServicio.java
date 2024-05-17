package com.ipfs.web.servicios;

import com.ipfs.web.entidades.Cliente;
import com.ipfs.web.excepciones.MiException;
import com.ipfs.web.repositorios.ClienteRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    //crear cliente
    @Transactional
    public void crearCliente(/*MultipartFile archivo*/String genero, String apellidoNombre, String relacion, String dni, String tel, String cp, String domicilio, String localidad, String provincia, String estadoCivil, String fechaNacimiento) throws MiException {

        validar(genero, apellidoNombre, relacion, dni, tel, cp, domicilio, localidad, provincia, estadoCivil, fechaNacimiento);

        Cliente cliente = new Cliente();

        cliente.setApellidoNombre(apellidoNombre);
        cliente.setFechaNacimiento(fechaNacimiento);
        cliente.setCp(cp);
        cliente.setDni(dni);
        cliente.setEstadoCivil(estadoCivil);
        cliente.setRelacion(relacion);
        cliente.setGenero(genero);
        cliente.setTel(tel);
        cliente.setProvincia(provincia);
        cliente.setLocalidad(localidad);
        cliente.setDomicilio(domicilio);

        /*oferta.setAltaOferta(new Date());*/

        /*Imagen imagen = imagenServicio.guardar(archivo);*/

        /*oferta.setImagen(imagen);*/
        clienteRepositorio.save(cliente);
    }
    
    //listar cliente 
    public List<Cliente> listarCliente() {

        List<Cliente> clientes = new ArrayList();

        clientes = clienteRepositorio.findAll(); //encuentra todo (findAll)

        return clientes;
    }
    
    //FUNCIONALIDAD PARA FILTROS DE CLIENTES(busqueda)
    public List<Cliente> listAll(String palabraClave) {
        if (palabraClave != null) {
            return clienteRepositorio.findAll(palabraClave);
        }

        return clienteRepositorio.findAll();
    }

    //modificar cliente
    @Transactional //se pasa el idCliente porq se necesita en modificarOferta
    public void modificarCLiente(/*MultipartFile archivo*/String idCliente, String genero, String apellidoNombre, String relacion, String dni, String tel, String cp, String domicilio, String localidad, String provincia, String estadoCivil, String fechaNacimiento) throws MiException {

       /*  validar(idCliente, genero, apellidoNombre, relacion, dni, tel, cp, domicilio, localidad, provincia, estadoCivil, fechaNacimiento);*/

        Optional<Cliente> respuesta = clienteRepositorio.findById(idCliente);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setApellidoNombre(apellidoNombre);
            cliente.setFechaNacimiento(fechaNacimiento);
            cliente.setCp(cp);
            cliente.setDni(dni);
            cliente.setEstadoCivil(estadoCivil);
            cliente.setRelacion(relacion);
            cliente.setGenero(genero);
            cliente.setTel(tel);
            cliente.setProvincia(provincia);
            cliente.setLocalidad(localidad);
            cliente.setDomicilio(domicilio);

            /*String idImagen = null;  //se le asigna null ??

            if (oferta.getImagen() != null) {
                idImagen = oferta.getImagen().getIdImagen();
            }

            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            oferta.setImagen(imagen);*/
            clienteRepositorio.save(cliente);
        }
    }

    public Cliente getOne(String idCliente) {
        return clienteRepositorio.getOne(idCliente); //conseguir uno
    }

    @Transactional
    public void borrarPorId(String idCliente) { //eliminar por num de id
        clienteRepositorio.deleteById(idCliente);
    }

    private void validar(/*String idOferta, MultipartFile archivo, */ String genero, String apellidoNombre, String relacion, String dni, String tel, String cp, String domicilio, String localidad, String provincia, String estadoCivil, String fechaNacimiento) throws MiException {


        if (genero.isEmpty() || genero == null) {
            throw new MiException("El genero no puede ser nulo o estar vacio");
        }
        if (apellidoNombre.isEmpty() || apellidoNombre == null) {
            throw new MiException("El apellido y nombre no puede ser nulo o estar vacio");
        }

        if (relacion.isEmpty() || relacion == null) {
            throw new MiException("relacion no puede ser nulo o estar vacio");
        }
        if (dni.isEmpty() || dni == null) {
            throw new MiException("el dni  no puede ser nulo o estar vacio");
        }
        if (tel.isEmpty() || tel == null) {
            throw new MiException("el telefono no puede ser nulo o estar vacio");
        }
        if (cp.isEmpty() || cp == null) {
            throw new MiException("el codigo postal no puede ser nulo o estar vacio");
        }
        if (domicilio.isEmpty() || domicilio == null) {
            throw new MiException("el domicilio no puede ser nulo o estar vacio");
        }
        if (localidad.isEmpty() || localidad == null) {
            throw new MiException("la localidad  no puede ser nulo o estar vacio");
        }
        if (provincia.isEmpty() || provincia == null) {
            throw new MiException("la provincia no puede ser nulo o estar vacio");
        }
        if (estadoCivil.isEmpty() || estadoCivil == null) {
            throw new MiException("el estodo civil no puede ser nulo o estar vacio");
        }
        if (fechaNacimiento.isEmpty() || fechaNacimiento == null) {
            throw new MiException("la fecha de nacimiento  no puede ser nulo o estar vacio");
        }
    }
}
