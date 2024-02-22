
package com.ipfs.web.repositorios;

import com.ipfs.web.entidades.Relevamiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RelevamientoRepositorio extends JpaRepository<Relevamiento, String>{
     @Query("SELECT r FROM Relevamiento r WHERE"
            + " CONCAT(r.idRelevamiento, r.agenciaSeguro, r.datosSiniestro, r.vehiculoAsegurado, r.vehiculoTercero, r.datosAsegurado, r.conductorVehiAsegurado, r.datosTitularTercero, r.conductorVehiTercero)"
            + " LIKE %?1%")
    public List<Relevamiento> findAll(String palabraClave);

}
