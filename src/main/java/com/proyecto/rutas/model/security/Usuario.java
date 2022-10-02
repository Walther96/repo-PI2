package com.proyecto.rutas.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.proyecto.rutas.model.Entity.DestinoEntity;
import com.proyecto.rutas.model.generic.GenericEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="USUARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Usuario extends GenericEntity{
	private static final long serialVersionUID = 1867820029796256757L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_USUARIO")
	@Builder.Default
	private Long id= 0L;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="APELLIDO")
	private String apellido;
	
	@Column(name="DNI")
	private String dni;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="USUARIO")
	private String usuario;
	
	@Column(name="CLAVE")
	private String clave;	
	
	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = true)
	private Rol rol;

}
