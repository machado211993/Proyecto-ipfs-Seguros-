
package com.ipfs.web.repositorios;

import com.ipfs.web.entidades.Relevamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelevamientoRepositorio extends JpaRepository<Relevamiento, String>{
    
}
