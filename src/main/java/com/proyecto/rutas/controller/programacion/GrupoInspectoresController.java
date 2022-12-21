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
import com.proyecto.rutas.model.Entity.DestinoEntity;
import com.proyecto.rutas.model.Entity.GrupoInspectoresEntity;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.DestinoService;
import com.proyecto.rutas.services.programacion.inf.GrupoInspectoresService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/grupoinspectores/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class GrupoInspectoresController extends GenericController{

	@Autowired
	private GrupoInspectoresService grupoInspectoresService;
	
	@GetMapping
	public  ResponseEntity<ResponseREST> getGruposInspectores() {
		try {
			List<GrupoInspectoresEntity> lst=this.grupoInspectoresService.getAll();
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
	public  ResponseEntity<ResponseREST> getGrupoInspector(@PathVariable Long id) {
		try {
			if (id<=0) {
				return super.getBadIdRequest();
			}
			GrupoInspectoresEntity grupoInspectores=this.grupoInspectoresService.findById(id);
			if (grupoInspectores==null) {
				return super.getNotFoundRequest();
			}
			return super.getOKConsultaRequest(grupoInspectores);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
	
	

	
	@PostMapping
	public ResponseEntity<ResponseREST> insertar( @Validated @RequestBody GrupoInspectoresEntity grupoInspectores, BindingResult result) {
		if (result.hasErrors()) {
			return super.getBadRequest(result);
		}
		try {
			GrupoInspectoresEntity oGrupoInspectores=grupoInspectoresService.save(grupoInspectores);
			if (oGrupoInspectores!=null) {
				return super.getCreatedRequest(oGrupoInspectores);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}

	@PutMapping	("/{id}")
	public ResponseEntity<ResponseREST> actualizar(@PathVariable Long id,@Validated @RequestBody GrupoInspectoresEntity grupoInspectores,
			BindingResult result) {
		if (id<=0) {
			return super.getBadIdRequest();
		}
		grupoInspectores.setId(id);
		
		if (result.hasErrors()) {
			return super.getBadRequest(result);
		}
		try {
			GrupoInspectoresEntity oGrupoInspectores=grupoInspectoresService.save(grupoInspectores);
			if (oGrupoInspectores!=null) {
				return super.getOKRegistroRequest(oGrupoInspectores);
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
			GrupoInspectoresEntity oGrupoInspectores=grupoInspectoresService.delete(id);
			if (oGrupoInspectores!=null) {
				return super.getOKRegistroRequest(oGrupoInspectores);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
}
