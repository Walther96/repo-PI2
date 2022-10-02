package com.proyecto.rutas.controller.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseREST {
	
	private String			apiVersion;	
	
	private ResponseEstado 	estado;
	
	@JsonInclude(Include.NON_NULL) 
	private Object 			resultado;
}
