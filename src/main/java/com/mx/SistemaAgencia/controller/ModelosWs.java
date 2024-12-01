package com.mx.SistemaAgencia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.SistemaAgencia.model.Modelos;
import com.mx.SistemaAgencia.service.ModelosSerImp;

@RestController
@RequestMapping(path = "ModelosWs")
@CrossOrigin
public class ModelosWs { 
	@Autowired
	ModelosSerImp modeloImp;
	@GetMapping(path =  "listar")
	public List<Modelos> listar(){
		return modeloImp.listar();
	}
	
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Modelos modelo) {
		String response = modeloImp.guardar(modelo);
		if (response.equals("idModeloExiste")) {
			return new ResponseEntity<>("Ese id modelo ya existe", HttpStatus.OK);
		}else if(response.equals("nombreModExiste")) {
			return new ResponseEntity<>("Ese nombre modelo ya existe", HttpStatus.OK);
		}else if(response.equals("idMarcaNoExiste")) {
			return new ResponseEntity<>("Ese idMarca no existe", HttpStatus.OK);
		}else {
			return new ResponseEntity<>(modelo, HttpStatus.CREATED);
		}
	}
	
	@PostMapping(path = "buscar")
	public Modelos buscar(@RequestBody Modelos modelo) {
		return modeloImp.buscar(modelo.getId());
	}
	
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Modelos modelo) {
		String response = modeloImp.editar(modelo);
	
		if (response.equals("idModeloExiste")) {
			return new ResponseEntity<>("Ese id modelo ya existe", HttpStatus.OK);
		}else if(response.equals("idMarcaNoExiste")) {
			return new ResponseEntity<>("Ese idMarca no existe", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Se edito con exito", HttpStatus.OK);
		}
	}
	@PostMapping("eliminar")
	public ResponseEntity <String> eliminar(@RequestBody Modelos modelo){
		boolean response = modeloImp.eliminar(modelo);
		
		if(response == true)
			return new ResponseEntity <>("Eliminado exitosamente", HttpStatus.OK);
		else
			return new ResponseEntity<>("No existe ese id de producto", HttpStatus.OK);
					
	}
}
