package com.mx.SistemaAgencia.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MODELOS_AGENCIA")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Modelos {
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "TIPO")
	private String tipo;
	
	@Column(name = "PRECIO")
	private Long  precio;
	
	@Column(name = "FECHA_LANZ")
	private Date fechaLanza;
	
	
	//Fetch con eso indicamos como debe ser cargada la entidad
	//FetchType.EAGER -- indicamos que la relacion debe ser cargado al momento(procesos hilos)
	//Cardinalidad
	//Muchos modelos pertenecen a una marca
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MARCA")
	Marcas marca; //Esta variable de tipo objeto debe coincidir con lo que dice en el mappedBy = "marcas"
}
