package com.proyecto.rutas.model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyecto.rutas.model.generic.GenericEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="DetalleRuta")
@Table(name="DETALLERUTA")
@Builder
public class DetalleRuta extends GenericEntity {
	
	@Id
	@Column(name="ID_DETALLE_RUTA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Builder.Default
	private Long id=0L;
	
	
	
	@Column(name="latitud")
	private Double lat;
	
	@Column(name="longitud")
	private Double lng;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_RUTA", nullable=false)
	@JsonIgnore // Redundancia cíclica
	private RutaEntity ruta; //Uno
		

}
