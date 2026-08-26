package cl.eva.springedumanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cl.eva.springedumanager.model.Estudiante;
import cl.eva.springedumanager.service.EstudianteService;

@Controller
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping("/estudiantes")
    public String listarEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.listarTodos());
        return "estudiantes";
    }

    @GetMapping("/estudiantes/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiante-form";
    }

    @PostMapping("/estudiantes/guardar")
    public String guardarEstudiante(
            @ModelAttribute("estudiante") Estudiante estudiante) {

        estudianteService.guardar(estudiante);
        return "redirect:/estudiantes";
    }
}