
package com.ipfs.web.repositorios;


import com.ipfs.web.entidades.Siniestro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiniestroRepositorio extends JpaRepository<Siniestro, String>{
    
}
