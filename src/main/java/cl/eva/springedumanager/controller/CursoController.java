package cl.eva.springedumanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cl.eva.springedumanager.model.Curso;
import cl.eva.springedumanager.service.CursoService;

@Controller
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/cursos")
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        return "cursos";
    }

    @GetMapping("/cursos/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("curso", new Curso());
        return "curso-form";
    }

    @PostMapping("/cursos/guardar")
    public String guardarCurso(@ModelAttribute("curso") Curso curso) {
        cursoService.guardar(curso);
        return "redirect:/cursos";
    }
}