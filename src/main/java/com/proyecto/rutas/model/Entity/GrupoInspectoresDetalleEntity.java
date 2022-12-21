package com.proyecto.rutas.model.Entity;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyecto.rutas.model.generic.GenericEntity;
import com.proyecto.rutas.model.security.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="GrupoInspectoresDetalle")
@Table(name="GRUPOINSPECTORESDETALLE")
@Builder
public class GrupoInspectoresDetalleEntity extends GenericEntity {
	
	@Id
	@Column(name="ID_DETALLE_GRUPOINSPECTORES")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Builder.Default
	private Long id = 0L;

	@ManyToOne
	@JoinColumn(name="ID_USUARIO", nullable=false)
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_GRUPOINSPECTORES", nullable=false)
	@JsonIgnore // Redundancia cíclica
	private GrupoInspectoresEntity grupoInspectores; //Uno

}
