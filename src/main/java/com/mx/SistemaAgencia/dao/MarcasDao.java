package com.mx.SistemaAgencia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.SistemaAgencia.model.Marcas;

public interface MarcasDao extends JpaRepository<Marcas,Long> {
	
}
