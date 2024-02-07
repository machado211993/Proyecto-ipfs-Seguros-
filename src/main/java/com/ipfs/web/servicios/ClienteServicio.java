package com.ipfs.web.servicios;

import com.ipfs.web.entidades.Cliente;
import com.ipfs.web.repositorios.ClienteRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    public List<Cliente> listarClientes() {
        return clienteRepositorio.findAll();

    }

    public void crearCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);

    }

    public Cliente obtenerClientePorId(String idCliente) {
        return clienteRepositorio.findById(idCliente).get();

    }

    public void eliminarCliente(String idCliente) {
        clienteRepositorio.deleteById(idCliente);
    }
}
