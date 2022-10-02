package com.proyecto.rutas.model.security;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.proyecto.rutas.model.generic.GenericEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name="MENU")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Menu extends GenericEntity{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_MENU")
	@Builder.Default
	private Long id = 0L;
	
	@Column(name="NOMBRE")
	private String nombre;
}
