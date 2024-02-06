package com.ipfs.web.repositorios;

import com.ipfs.web.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, String> {

}
