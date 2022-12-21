package com.proyecto.rutas.model.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="Papeleta")
@Table(name="PAPELETA")
@Builder
public class PapeletaEntity extends GenericEntity {
	
	@Id
	@Column(name="ID_PAPELETA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Builder.Default
	private Long id = 0L;

	@ManyToOne
	@JoinColumn(name="ID_INFRACCION", nullable=false)
	private InfraccionEntity infraccion;
	
	@ManyToOne
	@JoinColumn(name="ID_VEHICULO", nullable=false)
	private VehiculoEntity vehiculo;
	
	@Size(min=5, max=50, message="La camara es requerido y tiene que tener un mínimo de 5 caracteres")
	@Column(name="CAMARA", length = 50, nullable= false)
	private String camara;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA")
	@Builder.Default
	private Date fecha = new Date();
	
	@Column(name="DESCRIPCION", length = 2000, nullable= true)
	private String descripcion;
}
