package com.proyecto.rutas.services.programacion.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.rutas.model.Entity.DestinoEntity;
import com.proyecto.rutas.repository.DestinoRepository;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.DestinoService;

@Service
public class DestinoServiceImpl implements DestinoService {

	@Autowired
	private DestinoRepository destinorepository;
	
	
	
	@Override
	public DestinoEntity findById(Long id) throws ServiceException {

		return destinorepository.findById(id).orElse(null);
	}

	@Override
	public List<DestinoEntity> getAll() throws ServiceException {
		return destinorepository.findAllDestino();
	}

	@Override
	public DestinoEntity save(DestinoEntity destino) throws ServiceException {
		if (destino.getId()>0) {
			DestinoEntity oDestino= this.findById(destino.getId());
			BeanUtils.copyProperties(destino, oDestino);

		}
		
		return destinorepository.save(destino);
	}

	@Override
	public DestinoEntity delete(Long id) throws ServiceException {
		DestinoEntity oDestino= this.findById(id);
		oDestino.setEstado("0");
		return destinorepository.save(oDestino);
	}

}
