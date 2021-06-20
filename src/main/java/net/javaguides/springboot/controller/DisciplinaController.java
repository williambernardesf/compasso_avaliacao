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

import net.javaguides.springboot.model.Disciplina;
import net.javaguides.springboot.service.DisciplinaFuncao;

@Controller
@RequestMapping("disciplinas")
public class DisciplinaController {

	@Autowired
	private DisciplinaFuncao disciplinaFuncao;

	// display list of employees
	@GetMapping()
	public String viewHomePage(Model model) {
		return findPaginated(1, "nome", "asc", model);
	}

	@GetMapping("/mostraNovaDisciplinaForm")
	public String mostraNovaDisciplinaForm(Model model) {
		// create model attribute to bind form data
		Disciplina disciplina = new Disciplina();
		model.addAttribute("disciplina", disciplina);
		return "nova_disciplina";
	}

	@PostMapping("/registraDisciplina")
	public String registraDisciplina(@ModelAttribute("disciplina") Disciplina disciplina) {
		// save employee to database
		disciplinaFuncao.registraDisciplina(disciplina);
		return "redirect:/disciplinas";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

		// get employee from the service
		Disciplina disciplina = disciplinaFuncao.listaDisciplinaId(id);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("disciplina", disciplina);
		return "atualiza_disciplina";
	}

	@GetMapping("/deleteDisciplina/{id}")
	public String deleteDisciplina(@PathVariable(value = "id") long id) {

		// call delete employee method
		this.disciplinaFuncao.excluiDisciplinaId(id);
		return "redirect:/disciplinas";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<Disciplina> page = disciplinaFuncao.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Disciplina> listDisciplinas = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listDisciplinas", listDisciplinas);
		return "index_disciplina";
	}

}
