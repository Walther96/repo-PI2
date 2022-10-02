package com.proyecto.rutas.controller.commons;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.proyecto.rutas.controller.enums.ResponseEnums;

import lombok.Builder;
import lombok.Data;

@JsonPropertyOrder({"codigo", "mensaje","detalle","estado", "error", "fechaHora" })
@Data
@Builder
public class ResponseEstado {
	
	@Builder.Default
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Lima")
	private Date 			fechaHora= new Date(); 
	private Integer 		estado; 
	private String 			error; 
	private ResponseEnums	codigo;
	private Object 			mensaje; 
	@JsonInclude(Include.NON_NULL)
	private Object 			detalle;
}


