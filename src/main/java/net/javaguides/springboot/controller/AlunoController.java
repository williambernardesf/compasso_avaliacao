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

import net.javaguides.springboot.model.Aluno;
import net.javaguides.springboot.service.AlunoFuncao;

@Controller
@RequestMapping("alunos")
public class AlunoController {

	@Autowired
	private AlunoFuncao alunoFuncao;
	
	// display list of employees
	@GetMapping()
	public String viewHomePage(Model model) {
		return findPaginated(1, "nome", "asc", model);		
	}
	
	@GetMapping("/mostraNovoAlunoForm")
	public String mostraNovoAlunoForm(Model model) {
		// create model attribute to bind form data
		Aluno aluno = new Aluno();
		model.addAttribute("aluno", aluno);
		return "novo_aluno";
	}
	
	@PostMapping("/registraAluno")
	public String registraAluno(@ModelAttribute("aluno") Aluno aluno) {
		// save employee to database
		alunoFuncao.registraAluno(aluno);
		return "redirect:/alunos";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		Aluno aluno = alunoFuncao.listaAlunoId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("aluno", aluno);
		return "atualiza_aluno";
	}
	
	@GetMapping("/deleteAluno/{id}")
	public String deleteAluno(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.alunoFuncao.excluiAlunoId(id);
		return "redirect:/alunos";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Aluno> page = alunoFuncao.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Aluno> listAlunos = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listAlunos", listAlunos);
		return "index";
	}
}
