package com.proyecto.rutas.model.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

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
@Entity(name="GrupoInspectores")
@Table(name="GRUPOINSPECTORES")
@Builder
public class GrupoInspectoresEntity extends GenericEntity {
	
	@Id
	@Column(name="ID_GRUPOINSPECTORES")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Builder.Default
	private Long id = 0L;

	@Size(min=5, max=50, message="El codigo es requerido y debe ser mayor que 5 y máximo 50 caracteres")
	@Column(name="CODIGO", length = 50, nullable= false)
	private String codigo;
	
	
	@Size(min=5, max=50, message="El distrito es requerido y debe ser mayor que 5 y máximo 50 caracteres")
	@Column(name="DISTRITO", length = 50, nullable= false)
	private String distrito;
	
	
	@Size(min=5, max=50, message="La zona es requerida y debe ser mayor que 5 y máximo 50 caracteres")
	@Column(name="ZONA", length = 150, nullable= false)
	private String zona;
	
	@Size(min=5, max=50, message="El turno es requerido y debe ser mayor que 5 y máximo 50 caracteres")
	@Column(name="TURNO", length = 50, nullable= false)
	private String turno;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA")
	@Builder.Default
	private Date fecha = new Date();
	
	@OneToMany(mappedBy="grupoInspectores",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@Where(clause = "estado='1'")
    private List<GrupoInspectoresDetalleEntity> detalleGrupoInspectores;
	

}
