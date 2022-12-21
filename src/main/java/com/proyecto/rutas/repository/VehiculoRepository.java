package com.proyecto.rutas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.rutas.model.Entity.VehiculoEntity;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long> {

	@Query("select t from Vehiculo t where t.estado='1'")
	List<VehiculoEntity> findAllVehiculo();


	@Query("select t from Vehiculo t where t.placa=:placa")
	VehiculoEntity FindXPlaca(@Param("placa") String placa);
}
