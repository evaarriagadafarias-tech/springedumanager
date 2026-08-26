package cl.eva.springedumanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.eva.springedumanager.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}