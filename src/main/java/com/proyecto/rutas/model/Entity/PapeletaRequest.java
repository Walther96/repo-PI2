package com.proyecto.rutas.model.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.proyecto.rutas.model.generic.GenericEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PapeletaRequest extends GenericEntity{
	
	private InfraccionEntity infraccion;
	
	private String camara;
	
	private String descripcion;
	
	private String placa;

	private String modelo;
	
	private String marca;

}
