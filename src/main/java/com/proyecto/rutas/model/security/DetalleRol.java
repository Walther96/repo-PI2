package com.proyecto.rutas.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyecto.rutas.model.generic.GenericEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="DETALLEROL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DetalleRol extends GenericEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_DETALLE_ROL")
	@Builder.Default
	private Long id = 0L;
	
	@ManyToOne
	@JoinColumn(name="id_menu", nullable=false)
	private Menu menu;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_rol", nullable=false)
	@JsonIgnore
	private Rol rol;

}
