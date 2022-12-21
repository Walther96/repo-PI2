package com.proyecto.rutas.services.programacion.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.rutas.model.Entity.GrupoInspectoresEntity;
import com.proyecto.rutas.model.Entity.RutaEntity;
import com.proyecto.rutas.repository.GrupoInspectoresRepository;
import com.proyecto.rutas.repository.RutaRepository;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.GrupoInspectoresService;

@Service
public class GrupoInspectoresServiceImpl implements GrupoInspectoresService{

	@Autowired
	private GrupoInspectoresRepository grupoinspectoresrepository;
	
	@Override
	public GrupoInspectoresEntity findById(Long id) throws ServiceException {
		return grupoinspectoresrepository.findById(id).orElse(null);
	}

	@Override
	public List<GrupoInspectoresEntity> getAll() throws ServiceException {
		return grupoinspectoresrepository.findAllGrupoInspectores();
	}

	@Override
	public GrupoInspectoresEntity save(GrupoInspectoresEntity grupoInspectores) throws ServiceException {
		if (grupoInspectores.getId()>0) {
			GrupoInspectoresEntity oGrupoInspectores= this.findById(grupoInspectores.getId());
			BeanUtils.copyProperties(grupoInspectores, oGrupoInspectores);
		}
		grupoInspectores.getDetalleGrupoInspectores().forEach(item->item.setGrupoInspectores(grupoInspectores));

		return grupoinspectoresrepository.save(grupoInspectores);
	}

	@Override
	public GrupoInspectoresEntity delete(Long id) throws ServiceException {
		GrupoInspectoresEntity oGrupoInspectores= this.findById(id);
		oGrupoInspectores.setEstado("0");
		return grupoinspectoresrepository.save(oGrupoInspectores);
	}

	@Override
	public GrupoInspectoresEntity FindXCodigo(String codigo) {
		GrupoInspectoresEntity grupoInspectores = this.grupoinspectoresrepository.FindXCodigo(codigo);
		return grupoInspectores;
	}

	@Override
	public GrupoInspectoresEntity FindXDistrito(String distrito) {
		GrupoInspectoresEntity grupoInspectores = this.grupoinspectoresrepository.FindXDistrito(distrito);
		return grupoInspectores;
	}

}
