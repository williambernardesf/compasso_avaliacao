package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Curso;
import net.javaguides.springboot.service.CursoFuncao;

@Controller
@RequestMapping("cursos")
public class CursoController {
	
	@Autowired
	private CursoFuncao cursoFuncao;
	
	// display list of employees
		@GetMapping()
		public String viewHomePage(Model model) {
			return findPaginated(1, "nome", "asc", model);
		}

		@GetMapping("/mostraNovoCursoForm")
		public String mostraNovoCursoForm(Model model) {
			// create model attribute to bind form data
			Curso curso = new Curso();
			model.addAttribute("curso", curso);
			return "novo_curso";
		}

		@PostMapping("/registraCursos")
		public String registraCursos(@ModelAttribute("curso") Curso curso) {
			// save employee to database
			cursoFuncao.registraCurso(curso);
			return "redirect:/cursos";
		}

		@GetMapping("/showFormForUpdate/{id}")
		public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

			// get employee from the service
			Curso curso = cursoFuncao.listaCursoId(id);

			// set employee as a model attribute to pre-populate the form
			model.addAttribute("curso", curso);
			return "atualiza_curso";
		}

		@GetMapping("/deleteCurso/{id}")
		public String deleteCurso(@PathVariable(value = "id") long id) {

			// call delete employee method
			this.cursoFuncao.excluiCursoId(id);
			return "redirect:/cursos";
		}

		@GetMapping("/page/{pageNo}")
		public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir, Model model) {
			int pageSize = 5;

			Page<Curso> page = cursoFuncao.findPaginated(pageNo, pageSize, sortField, sortDir);
			List<Curso> listEmployees = page.getContent();

			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());

			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

			model.addAttribute("listEmployees", listEmployees);
			return "index_curso";
		}


}
