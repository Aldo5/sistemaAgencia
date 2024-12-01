package com.mx.SistemaAgencia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mx.SistemaAgencia.dao.MarcasDao;
import com.mx.SistemaAgencia.dao.ModelosDao;
import com.mx.SistemaAgencia.model.Marcas;
import com.mx.SistemaAgencia.model.Modelos;

@Service

public class ModelosSerImp {
	@Autowired
	ModelosDao modeloDao;
	
	@Autowired
	MarcasDao marcaDao;
	@Transactional(readOnly = true)
	public List<Modelos> listar(){
		return modeloDao.findAll();
	}


@Transactional
public String guardar(Modelos modelo) {
	boolean banderaMar = false;
	boolean banderaMod = false;
	String respuesta = "";
	
	for(Marcas mar: marcaDao.findAll()) {
		if(mar.getId().equals(modelo.getMarca().getId())) {
			banderaMar = true;
			for(Modelos mod: modeloDao.findAll()) {
				if(mod.getId().equals(modelo.getId())) {
					banderaMod = true;
					respuesta = "idModeloExiste";
							break;
				}else if (mod.getNombre().equals(modelo.getNombre())) {
					banderaMod = true;
					respuesta = "nombreModExiste";
					break;
				}
			}
			break;
		}
	}
	if(banderaMar == false) {
		respuesta = "idMarcaNoExiste";
		banderaMod = true;
	}else if(banderaMod == false) {
		modeloDao.save(modelo);
		respuesta = "guardado";
	}
	return respuesta;
}  

@Transactional(readOnly = true)
public Modelos buscar(Long id) {
	return modeloDao.findById(id).orElse(null);
}


@Transactional
public String editar (Modelos modelo) {
	
	boolean banderaMar = false;
	boolean banderaMod = false;
	String respuesta = "";
	
	for(Marcas mar: marcaDao.findAll()) {
		if(mar.getId().equals(modelo.getMarca().getId())) {
			banderaMar = true;
			for(Modelos mod: modeloDao.findAll()) {
				if(mod.getId().equals(modelo.getId())) {
					banderaMod = true;
							break;
			}
			}
			break;
		}
	}
	if(banderaMar == false) {
		respuesta = "idMarcaNoExiste";
		banderaMod = false;
	}else if(banderaMod == false) {
		respuesta = "idModeloNoExiste";
	}else if (banderaMar ==  true && banderaMod == true) {
		modeloDao.save(modelo);
	}
	return respuesta;
}

@Transactional
public boolean eliminar(Modelos modelo) {
	boolean bandera = false;
	for (Modelos m : modeloDao.findAll()) {
		if (m.getId().equals(modelo.getId())) {
			modeloDao.delete(modelo);
			bandera = true;
			break;
		}
	}
	return bandera;

}

}
