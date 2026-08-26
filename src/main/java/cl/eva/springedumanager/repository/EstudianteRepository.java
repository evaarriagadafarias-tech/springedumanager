package cl.eva.springedumanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.eva.springedumanager.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

}