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
@Entity(name="Infraccion")
@Table(name="INFRACCION")
@Builder
public class InfraccionEntity extends GenericEntity {
	
	@Id
	@Column(name="ID_INFRACCION")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Builder.Default
	private Long id = 0L;

	@Size(min=5, max=50, message="El código es requerido y tiene que tener un mínimo de 5 caracteres")
	@Column(name="CODIGO", length = 50, nullable= false)
	private String codigo;
	
	@Size(min=3, max=50, message="El nivel es requerido y tiene que tener un mínimo de 3 caracteres")
	@Column(name="NIVEL", length = 50, nullable= false)
	private String nivel;
	
	@Column(name="DESCRIPCION", length = 2000, nullable= true)
	private String descripcion;
}
