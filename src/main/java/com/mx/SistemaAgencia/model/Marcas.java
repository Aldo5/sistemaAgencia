package com.mx.SistemaAgencia.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MARCAS_AGENCIA")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Marcas {
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "ORIGEN")
	private String  origen;
	
	@Column(name = "ESLOGAN")
	private String eslogan;
	
	//Cardinalidas
	//Una marca tiene modelos
	@OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
	@JsonIgnore//Se agrega para omitir una propiedad o lista de propiedades
	
	List<Modelos> lista = new ArrayList<Modelos>();
}
