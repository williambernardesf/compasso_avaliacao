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

import net.javaguides.springboot.model.Professor;
import net.javaguides.springboot.service.ProfessorFuncao;

@Controller
@RequestMapping("professores")
public class ProfessorController {
	
	@Autowired
	private ProfessorFuncao professorFuncao;
	
	@GetMapping()
	public String viewHomePage(Model model) {
		return findPaginated(1, "nome", "asc", model);
	}

	@GetMapping("/mostraNovoProfessorForm")
	public String mostraNovoProfessorForm(Model model) {
		// create model attribute to bind form data
		Professor professor = new Professor();
		model.addAttribute("professor", professor);
		return "novo_professor";
	}

	@PostMapping("/registraProfessor")
	public String registraProfessor(@ModelAttribute("professor") Professor professor) {
		// save employee to database
		professorFuncao.registraProfessor(professor);
		return "redirect:/professores";

	}
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

		// get employee from the service
		Professor professor = professorFuncao.listaProfessorId(id);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("professor", professor);
		return "atualiza_professor";
	}

	@GetMapping("/deleteProfessor/{id}")
	public String deleteProfessor(@PathVariable(value = "id") long id) {

		// call delete employee method
		this.professorFuncao.excluiProfessorId(id);
		return "redirect:/professores";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<Professor> page = professorFuncao.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Professor> listProfessor = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listProfessor", listProfessor);
		return "index_professor";
	}


}
