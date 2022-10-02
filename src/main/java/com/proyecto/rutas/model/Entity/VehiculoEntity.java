package com.proyecto.rutas.model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Entity(name="Vehiculo")
@Table(name="VEHICULO")
@Builder
public class VehiculoEntity extends GenericEntity {
	
	@Id
	@Column(name="ID_VEHICULO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Builder.Default
	private Long id = 0L;
	
	@Size(min=6, max=6, message="La placa es requerida y debe tener 6 caracteres")
	@Column(name="PLACA",  length = 6, nullable= false, unique = true)
	private String placa;

	@Size(min=4, max=50, message="El modelo es requerido y debe ser mayor que 4 y máximo 50 caracteres")
	@Column(name="MODELO",  length = 50, nullable= false)
	private String modelo;
	
	@Size(min=4, max=50, message="La marca es requerida y debe ser mayor que 4 y máximo 50 caracteres")
	@Column(name="MARCA",  length = 50, nullable= false)
	private String marca;
	





	
}
