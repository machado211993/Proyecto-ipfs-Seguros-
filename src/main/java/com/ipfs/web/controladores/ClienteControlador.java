package com.ipfs.web.controladores;

import com.ipfs.web.entidades.Cliente;
import com.ipfs.web.servicios.ClienteServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/cliente")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/listar")
    public List<Cliente> listarCliente() {

        return clienteServicio.listarClientes();
    }

    @GetMapping("/buscar/{idCliente}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable String idCliente) {
        try {
            Cliente cliente = clienteServicio.obtenerClientePorId(idCliente);
            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        } catch (Exception MiException) {
            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/registrar")
    public void guardarCliente(@RequestBody Cliente cliente) {
        clienteServicio.crearCliente(cliente);
    }

    @PutMapping("/modificar/{idCliente}")
    public ResponseEntity<Cliente> actualizarCliente(@RequestBody Cliente cliente, @PathVariable String idCliente) {
        try {
            Cliente clienteExistente = clienteServicio.obtenerClientePorId(idCliente);
            clienteServicio.crearCliente(cliente);
            return new ResponseEntity<Cliente>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{idCliente}")
    public void eliminarCliente(@PathVariable String idCliente) {
        clienteServicio.eliminarCliente(idCliente);
    }
}
