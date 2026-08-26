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

import cl.eva.springedumanager.model.Curso;
import cl.eva.springedumanager.service.CursoService;

@RestController
@RequestMapping("/api/cursos")
public class CursoRestController {

    private final CursoService cursoService;

    public CursoRestController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> listar() {
        return cursoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscar(@PathVariable Long id) {
        return cursoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Curso crear(@RequestBody Curso curso) {
        return cursoService.guardar(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(
            @PathVariable Long id,
            @RequestBody Curso curso) {

        if (cursoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        curso.setId(id);
        return ResponseEntity.ok(cursoService.guardar(curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (cursoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}