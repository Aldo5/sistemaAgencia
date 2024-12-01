package com.mx.SistemaAgencia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.SistemaAgencia.model.Marcas;
import com.mx.SistemaAgencia.service.MarcaServImp;

@RestController
@RequestMapping(path = "MarcasWs")
@CrossOrigin
public class MarcasWs {
	@Autowired
	MarcaServImp marcaImp;
	
	@GetMapping("listar")
	public List<Marcas> listar(){
		return marcaImp.listar();
	}
	
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Marcas marca) {
	    // Llamar al método guardar y obtener el mensaje de respuesta
	    String response = marcaImp.guardar(marca);

	    
	    if (response.equals("El ID ya existe")) {
	        return new ResponseEntity<>("El Id de esa marca ya existe", HttpStatus.OK);
	    } else if (response.equals("El nombre ya existe")) {
	        return new ResponseEntity<>("El nombre de esa marca ya existe", HttpStatus.OK);
	    } else if (response.equals("Marca guardada con éxito")) {
	        return new ResponseEntity<>(marca, HttpStatus.CREATED);
	    }

	    // Respuesta por defecto (caso no esperado)
	    return new ResponseEntity<>("Error desconocido", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@PostMapping(path = "buscar")
	public Marcas buscar(@RequestBody Marcas marca) {
		return marcaImp.buscar(marca.getId());
	}
	
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Marcas marca){
		
		boolean response = marcaImp.editar(marca);
		
		if(response == true)
			return new ResponseEntity <>(marca, HttpStatus.CREATED);
		else
			return new ResponseEntity<>("No existe ese id de esa marca", HttpStatus.OK);	
	}
	
	@PostMapping(path = "eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Marcas marca){
		
		boolean response = marcaImp.eliminar(marca.getId());
		
		if(response == true)
			return new ResponseEntity <>("Eliminado exitosamente", HttpStatus.OK);
		else
			return new ResponseEntity<>("No existe ese id de esa marca", HttpStatus.OK);
		
	
	}
}
