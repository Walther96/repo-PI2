package com.proyecto.rutas.services.programacion.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.rutas.model.Entity.VehiculoEntity;
import com.proyecto.rutas.repository.VehiculoRepository;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.VehiculoService;

@Service
public class VehiculoServiceImpl implements VehiculoService {

	@Autowired
	private VehiculoRepository vehiculorepository;
	
	@Override
	public VehiculoEntity findById(Long id) throws ServiceException {
		return vehiculorepository.findById(id).orElse(null);
	}

	@Override
	public List<VehiculoEntity> getAll() throws ServiceException {
		return vehiculorepository.findAllVehiculo();
	}

	@Override
	public VehiculoEntity save(VehiculoEntity vehiculo) throws ServiceException {
		if (vehiculo.getId()>0) {
			VehiculoEntity oVehiculo= this.findById(vehiculo.getId());
			BeanUtils.copyProperties(vehiculo, oVehiculo);
		}
		return vehiculorepository.save(vehiculo);
	}

	@Override
	public VehiculoEntity delete(Long id) throws ServiceException {
		VehiculoEntity oVehiculo= this.findById(id);
		oVehiculo.setEstado("0");
		return vehiculorepository.save(oVehiculo);
	}

	@Override
	public VehiculoEntity FindXPlaca(String placa) {
		VehiculoEntity vehiculo = this.vehiculorepository.FindXPlaca(placa);
		return vehiculo;
	}

	

}
