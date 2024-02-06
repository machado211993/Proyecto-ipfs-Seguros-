
package com.ipfs.web.repositorios;

import com.ipfs.web.entidades.Vehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehiculoRepositorio extends JpaRepository<Vehiculo, String>{
    @Query("SELECT v FROM Vehiculo v WHERE"
            + " CONCAT(v.idVehiculo, v.aseguradora, v.dominio, v.marca, v.modelo, v.tipo, v.color, v.año, v.dañosMateriales, v.lesiones, v.muerte, v.imagen)"
            + " LIKE %?1%")
    public List<Vehiculo> findAll(String palabraClave);
}
