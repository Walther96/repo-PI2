package com.proyecto.rutas.model.generic;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@MappedSuperclass
public class GenericEntity {
	@Size(min=1, max=1, message="El estado es requerido y debe ser 0 o 1")
	@Column(name="ESTADO")
	private String estado="1";
}
