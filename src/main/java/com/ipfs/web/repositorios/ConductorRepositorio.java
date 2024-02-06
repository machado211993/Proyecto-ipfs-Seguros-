package com.ipfs.web.repositorios;

import com.ipfs.web.entidades.Conductor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConductorRepositorio extends JpaRepository<Conductor, String> {

}
