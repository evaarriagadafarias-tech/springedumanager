package cl.eva.springedumanager.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.eva.springedumanager.model.Estudiante;
import cl.eva.springedumanager.service.EstudianteService;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteRestController {

    private final EstudianteService estudianteService;

    public EstudianteRestController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public List<Estudiante> listar() {
        return estudianteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscar(@PathVariable Long id) {
        return estudianteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estudiante crear(@RequestBody Estudiante estudiante) {
        return estudianteService.guardar(estudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(
            @PathVariable Long id,
            @RequestBody Estudiante estudiante) {

        if (estudianteService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        estudiante.setId(id);
        return ResponseEntity.ok(estudianteService.guardar(estudiante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (estudianteService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}