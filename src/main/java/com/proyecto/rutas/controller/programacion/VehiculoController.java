package com.proyecto.rutas.controller.programacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.rutas.controller.commons.ResponseREST;
import com.proyecto.rutas.controller.generic.GenericController;
import com.proyecto.rutas.model.Entity.VehiculoEntity;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.VehiculoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/vehiculo/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class VehiculoController extends GenericController {
	
	@Autowired
	private VehiculoService vehiculoService;
	
	
	
	@GetMapping
	public  ResponseEntity<ResponseREST> getVehiculos() {
		try {
			List<VehiculoEntity> lst=this.vehiculoService.getAll();
			if (lst.isEmpty()) {
				return super.getNotFoundRequest();
			}
			return super.getOKConsultaRequest(lst);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}

	@GetMapping("/{id}")
	public  ResponseEntity<ResponseREST> getVehiculo(@PathVariable Long id) {
		try {
			if (id<=0) {
				return super.getBadIdRequest();
			}
			VehiculoEntity vehiculo=this.vehiculoService.findById(id);
			if (vehiculo==null) {
				return super.getNotFoundRequest();
			}
			return super.getOKConsultaRequest(vehiculo);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
	
	

	
	@PostMapping
	public ResponseEntity<ResponseREST> insertar( @Validated @RequestBody VehiculoEntity vehiculo, BindingResult result) {
		if (result.hasErrors()) {
			return super.getBadRequest(result);
		}
		try {
			
			
			VehiculoEntity oVehiculo=vehiculoService.save(vehiculo);
			if (oVehiculo!=null) {
				return super.getCreatedRequest(oVehiculo);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}

	@PutMapping	("/{id}")
	public ResponseEntity<ResponseREST> actualizar(@PathVariable Long id,@Validated @RequestBody VehiculoEntity vehiculo,
			BindingResult result) {
		if (id<=0) {
			return super.getBadIdRequest();
		}
		vehiculo.setId(id);
		
		if (result.hasErrors()) {
			return super.getBadRequest(result);
		}
		try {
			VehiculoEntity oVehiculo=vehiculoService.save(vehiculo);
			if (oVehiculo!=null) {
				return super.getOKRegistroRequest(oVehiculo);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseREST> eliminar(@PathVariable Long id) {
		if (id<=0) {
			return ResponseEntity.badRequest().build();
		}
		try {
			VehiculoEntity oVehiculo=vehiculoService.delete(id);
			if (oVehiculo!=null) {
				return super.getOKRegistroRequest(oVehiculo);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
	
	

}
