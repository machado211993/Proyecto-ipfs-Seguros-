package com.ipfs.web.repositorios;

import com.ipfs.web.entidades.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepositorio extends JpaRepository<Cliente, String> {

    @Query("SELECT c FROM Cliente c WHERE"
            + " CONCAT(c.idCliente, c.genero, c.apellidoNombre, c.relacion, c.dni, c.tel, c.cp, c.domicilio, c.localidad, c.provincia, c.estadoCivil, c.fechaNacimiento)"
            + " LIKE %?1%")
    public List<Cliente> findAll(String palabraClave);
}
