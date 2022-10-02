package com.proyecto.rutas.services.programacion.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.rutas.model.Entity.RutaEntity;
import com.proyecto.rutas.repository.RutaRepository;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.RutaService;

@Service
public class RutaServiceImpl implements RutaService {

	@Autowired
	private RutaRepository rutarepository;
	
	@Override
	public RutaEntity findById(Long id) throws ServiceException {
		return rutarepository.findById(id).orElse(null);
	}

	@Override
	public List<RutaEntity> getAll() throws ServiceException {
		return rutarepository.findAllRuta();
	}

	@Override
	public RutaEntity save(RutaEntity ruta) throws ServiceException {
		if (ruta.getId()>0) {
			RutaEntity oRuta= this.findById(ruta.getId());
			BeanUtils.copyProperties(ruta, oRuta);
		}
		ruta.getDetalleruta().forEach(item->item.setRuta(ruta));

		return rutarepository.save(ruta);
	}

	@Override
	public RutaEntity delete(Long id) throws ServiceException {
		RutaEntity oRuta= this.findById(id);
		oRuta.setEstado("0");
		return rutarepository.save(oRuta);
	}

	@Override
	public RutaEntity FindRutaXNombre(String nombre) {
		RutaEntity ruta = this.rutarepository.FindRutaXNombre(nombre);
		return ruta;
	}

}
