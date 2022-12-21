package com.proyecto.rutas.services.programacion.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.rutas.model.Entity.PapeletaEntity;
import com.proyecto.rutas.model.Entity.PapeletaRequest;
import com.proyecto.rutas.model.Entity.VehiculoEntity;
import com.proyecto.rutas.repository.PapeletaRepository;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.PapeletaService;
import com.proyecto.rutas.services.programacion.inf.VehiculoService;

@Service
public class PapeletaServiceImpl implements PapeletaService{

	@Autowired
	private PapeletaRepository papeletarepository;
	
	@Autowired
	private VehiculoService vehiculoService;;
	
	@Override
	public PapeletaEntity findById(Long id) throws ServiceException {

		return papeletarepository.findById(id).orElse(null);
	}

	@Override
	public List<PapeletaEntity> getAll() throws ServiceException {
		return papeletarepository.findAllPapeletas();
	}

	@Override
	public PapeletaEntity savePapeleta(PapeletaRequest request) throws ServiceException {
		
		VehiculoEntity vehiculoEntity = existVehiculo(request);
		
		PapeletaEntity papeleta = new PapeletaEntity();
		
		if(vehiculoEntity!=null) {
			papeleta = PapeletaRequestAPapeletaEntity(request, vehiculoEntity);
		}else {
			
			vehiculoEntity = InsertVehiculo(request);
			papeleta = PapeletaRequestAPapeletaEntity(request, vehiculoEntity);

		}
		
		
		
		return papeletarepository.save(papeleta);
	}
	
	public PapeletaEntity update(PapeletaEntity papeleta) throws ServiceException{
		if (papeleta.getId()>0) {
			PapeletaEntity oPapeleta= this.findById(papeleta.getId());
			BeanUtils.copyProperties(papeleta, oPapeleta);
			return papeletarepository.save(papeleta);

		}else {
			return null;
		}
		
	}

	@Override
	public PapeletaEntity delete(Long id) throws ServiceException {
		PapeletaEntity oPapeleta= this.findById(id);
		oPapeleta.setEstado("0");
		return papeletarepository.save(oPapeleta);
	}
	
	
	public VehiculoEntity existVehiculo(PapeletaRequest request) {
		
		VehiculoEntity vehiculo = vehiculoService.FindXPlaca(request.getPlaca());
		
		return vehiculo;
		
	}
	
	public PapeletaEntity PapeletaRequestAPapeletaEntity(PapeletaRequest request, VehiculoEntity vehiculoEntity) {
		
		PapeletaEntity papeleta = PapeletaEntity.builder()
									.infraccion(request.getInfraccion())
									.vehiculo(vehiculoEntity)
									.camara(request.getCamara())
									.descripcion(request.getDescripcion())
									.build();
		return papeleta;
	}
	
	public VehiculoEntity InsertVehiculo(PapeletaRequest request) throws ServiceException {
		
		VehiculoEntity requestVehiculo = VehiculoEntity.builder()
												.placa(request.getPlaca())
												.modelo(request.getModelo())
												.marca(request.getMarca())
												.build();
		
		VehiculoEntity vehiculo = vehiculoService.save(requestVehiculo);
		return vehiculo;
		
	}

	@Override
	public PapeletaEntity save(PapeletaEntity papeleta) throws ServiceException {
		if (papeleta.getId()>0) {
			PapeletaEntity oPapeleta= this.findById(papeleta.getId());
			BeanUtils.copyProperties(papeleta, oPapeleta);

		}
		
		return papeletarepository.save(papeleta);
	}

	@Override
	public List<PapeletaEntity> findByFecha(Date desde, Date hasta) throws ServiceException {
		return papeletarepository.findByFecha(desde,hasta);

	}
}
