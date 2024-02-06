
package com.ipfs.web.repositorios;

import com.ipfs.web.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenRepositorio extends JpaRepository<Imagen, String>{
    
}
