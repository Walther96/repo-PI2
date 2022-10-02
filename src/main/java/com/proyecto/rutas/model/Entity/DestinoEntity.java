package com.proyecto.rutas.model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@Entity(name="Destino")
@Table(name="DESTINO")
@Builder
public class DestinoEntity extends GenericEntity {
	
	@Id
	@Column(name="ID_DESTINO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Builder.Default
	private Long id = 0L;

	@Size(min=5, max=50, message="La descripcion es requerido y debe ser mayor que 5 y máximo 50 caracteres")
	@Column(name="DESCRIPCION", length = 50, nullable= false)
	private String descripcion;
	

}
