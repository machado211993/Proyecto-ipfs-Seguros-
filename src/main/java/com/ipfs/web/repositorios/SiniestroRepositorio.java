
package com.ipfs.web.repositorios;


import com.ipfs.web.entidades.Siniestro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SiniestroRepositorio extends JpaRepository<Siniestro, String>{
    @Query("SELECT s FROM Siniestro s WHERE"
            + " CONCAT(s.idSiniestro, s.polizaNumero, s.numeroSiniestro, s.fechaSiniestro, s.horaSiniestro, s.lugarHecho, s.estadoTiempo, s.comisaria, s.provincia)"
            + " LIKE %?1%")
    public List<Siniestro> findAll(String palabraClave);
}
