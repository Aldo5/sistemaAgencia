package com.mx.SistemaAgencia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.SistemaAgencia.dao.MarcasDao;
import com.mx.SistemaAgencia.model.Marcas;

@Service
public class MarcaServImp {
	@Autowired
	MarcasDao marcaDao;
	
	
	@Transactional(readOnly = true)
	public List <Marcas> listar(){
		return marcaDao.findAll();
	}
	
	//Validar que el id y nombre no se repita
	@Transactional
	public String guardar(Marcas marca) {
	    boolean bandera = false;
	    String mensaje = "";

	    
	    for (Marcas p : marcaDao.findAll()) {
	        if (p.getId().equals(marca.getId())) {
	            mensaje = "El ID ya existe";
	            bandera = true;
	            break; 
	        } else if (p.getNombre().equals(marca.getNombre())) {
	            mensaje = "El nombre ya existe";
	            bandera = true;
	            break; 
	        }
	    }

	    if (bandera) {
	        return mensaje;
	    }

	    marcaDao.save(marca);
	    return "Marca guardada con éxito";
	}


		
	
	@Transactional(readOnly = true)
	public Marcas buscar(Long id) {
		
		return marcaDao.findById(id).orElse(null);
	}

	//Validar: validar que el id existe editar, contrario no editar
	@Transactional
	public boolean editar(Marcas marca) {
		boolean bandera = false;
		for (Marcas m : marcaDao.findAll()) {
			if (m.getId().equals(marca.getId())) {
				marcaDao.save(marca);
				bandera = true;
				break;
			}
		}
		return bandera;
		}
	
	//Validar: validar que el id exista, contrario eliminar
	public boolean eliminar (Long id) {
		
		boolean bandera = false;
		for (Marcas m : marcaDao.findAll()) {
			if (m.getId().equals(id)) {
				// repository.delete(producto);
				marcaDao.deleteById(id);
				bandera = true;
				break;
			}
		}
		return bandera;
	}

	}

