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

import net.javaguides.springboot.model.Turma;
import net.javaguides.springboot.service.TurmaFuncao;

@Controller
@RequestMapping("turmas")
public class TurmaController {

		@Autowired
		private TurmaFuncao turmaFuncao;

		// display list of employees
		@GetMapping()
		public String viewHomePage(Model model) {
			return findPaginated(1, "nome", "asc", model);
		}

		@GetMapping("/mostraNovaTurmaForm")
		public String mostraNovaTurmaForm(Model model) {
			// create model attribute to bind form data
			Turma turma = new Turma();
			model.addAttribute("turma", turma);
			return "nova_turma";
		}

		@PostMapping("/registraTurma")
		public String registraDisciplina(@ModelAttribute("turma") Turma turma) {
			// save employee to database
			turmaFuncao.registraTurma(turma);
			return "redirect:/turmas";
		}

		@GetMapping("/showFormForUpdate/{id}")
		public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

			// get employee from the service
			Turma turma = turmaFuncao.listaTurmaId(id);

			// set employee as a model attribute to pre-populate the form
			model.addAttribute("turma", turma);
			return "atualiza_turma";
		}

		@GetMapping("/deleteTurma/{id}")
		public String deleteTurma(@PathVariable(value = "id") long id) {

			// call delete employee method
			this.turmaFuncao.excluiTurmaId(id);
			return "redirect:/turmas";
		}

		@GetMapping("/page/{pageNo}")
		public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir, Model model) {
			int pageSize = 5;

			Page<Turma> page = turmaFuncao.findPaginated(pageNo, pageSize, sortField, sortDir);
			List<Turma> listTurmas = page.getContent();

			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());

			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

			model.addAttribute("listTurmas", listTurmas);
			return "index_turma";
		}

}