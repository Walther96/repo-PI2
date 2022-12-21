package com.proyecto.rutas.controller.programacion;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.proyecto.rutas.model.Entity.PapeletaEntity;
import com.proyecto.rutas.model.Entity.PapeletaRequest;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.PapeletaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/papeleta/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class PapeletaController extends GenericController{

	@Autowired
	private PapeletaService papeletaService;
	
	@GetMapping
	public  ResponseEntity<ResponseREST> getPapeletas() {
		try {
			List<PapeletaEntity> lst=this.papeletaService.getAll();
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
	public  ResponseEntity<ResponseREST> getPapeleta(@PathVariable Long id) {
		try {
			if (id<=0) {
				return super.getBadIdRequest();
			}
			PapeletaEntity papeleta=this.papeletaService.findById(id);
			if (papeleta==null) {
				return super.getNotFoundRequest();
			}
			return super.getOKConsultaRequest(papeleta);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
	
	@GetMapping("/{desde}/{hasta}")
	public ResponseEntity<ResponseREST> findByFechacreacion(@PathVariable @DateTimeFormat(pattern = "yyyyMMdd") Date desde,
			@PathVariable @DateTimeFormat(pattern = "yyyyMMdd") Date hasta) {

		
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(desde);
			cal.set(Calendar.HOUR_OF_DAY, 0);

			Date vdesde = cal.getTime();

			cal.setTime(hasta);
			cal.set(Calendar.HOUR_OF_DAY, 24);
			Date vhasta = cal.getTime();
			
			List<PapeletaEntity> lst=this.papeletaService.findByFecha(vdesde, vhasta);
			if (lst.isEmpty()) {
				return super.getNotFoundRequest();
			}
			return super.getOKConsultaRequest(lst);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
		
		

	}

	
	@PostMapping
	public ResponseEntity<ResponseREST> insertar( @Validated @RequestBody PapeletaRequest papeleta, BindingResult result) {
		if (result.hasErrors()) {
			return super.getBadRequest(result);
		}
		try {
			PapeletaEntity oPapeleta=papeletaService.savePapeleta(papeleta);
			if (oPapeleta!=null) {
				return super.getCreatedRequest(oPapeleta);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}

	@PutMapping	("/{id}")
	public ResponseEntity<ResponseREST> actualizar(@PathVariable Long id,@Validated @RequestBody PapeletaEntity papeleta,
			BindingResult result) {
		if (id<=0) {
			return super.getBadIdRequest();
		}
		papeleta.setId(id);
		
		if (result.hasErrors()) {
			return super.getBadRequest(result);
		}
		try {
			PapeletaEntity oPapeleta=papeletaService.save(papeleta);
			if (oPapeleta!=null) {
				return super.getOKRegistroRequest(oPapeleta);
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
			PapeletaEntity oPapeleta=papeletaService.delete(id);
			if (oPapeleta!=null) {
				return super.getOKRegistroRequest(oPapeleta);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
}
