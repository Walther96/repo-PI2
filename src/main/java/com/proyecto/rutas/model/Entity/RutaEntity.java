package com.proyecto.rutas.model.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@Entity(name="Ruta")
@Table(name="RUTA")
@Builder
public class RutaEntity extends GenericEntity {
	
	@Id
	@Column(name="ID_RUTA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Builder.Default
	private Long id = 0L;
	
	@Size(min=5, max=100, message="El nombre de ruta es requerido y debe ser mayor que 5 y máximo 100 caracteres")
	@Column(name="NOMBRE_RUTA",  length = 100, nullable= false, unique=true)
	private String nombreruta;
	
	@ManyToOne
	@JoinColumn(name="ID_ORIGEN", nullable=false)
	private OrigenEntity origen;
	
	@ManyToOne
	@JoinColumn(name="ID_DESTINO", nullable=false)
	private DestinoEntity destino;

	@OneToMany(mappedBy="ruta",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@Where(clause = "estado='1'")
    private List<DetalleRuta> detalleruta;
}
