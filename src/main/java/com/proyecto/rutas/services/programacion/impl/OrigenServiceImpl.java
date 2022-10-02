package com.proyecto.rutas.services.programacion.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.rutas.model.Entity.OrigenEntity;
import com.proyecto.rutas.repository.OrigenRepository;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.OrigenService;

@Service
public class OrigenServiceImpl implements OrigenService {

	@Autowired
	private OrigenRepository origenrepository;
	
	@Override
	public OrigenEntity findById(Long id) throws ServiceException {

		return origenrepository.findById(id).orElse(null);
	}

	@Override
	public List<OrigenEntity> getAll() throws ServiceException {
		return origenrepository.findAllOrigen();
	}

	@Override
	public OrigenEntity save(OrigenEntity origen) throws ServiceException {
		if (origen.getId()>0) {
			OrigenEntity oOrigen= this.findById(origen.getId());
			BeanUtils.copyProperties(origen, oOrigen);
		}
		return origenrepository.save(origen);
	}

	@Override
	public OrigenEntity delete(Long id) throws ServiceException {
		OrigenEntity oOrigen= this.findById(id);
		oOrigen.setEstado("0");
		return origenrepository.save(oOrigen);
	}

}
