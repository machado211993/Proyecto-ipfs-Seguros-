package com.ipfs.web.repositorios;

import com.ipfs.web.entidades.Relevamiento;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RelevamientoRepositorio extends JpaRepository<Relevamiento, String> {

    @Query("SELECT r FROM Relevamiento r WHERE"
            + " CONCAT(r.idRelevamiento, r.agenciaSeguro, r.conductor, r.vehiculo, r.vehiculo, r.siniestro, r.cliente)"
            + " LIKE %?1%")
    public List<Relevamiento> findAll(String palabraClave);

}
