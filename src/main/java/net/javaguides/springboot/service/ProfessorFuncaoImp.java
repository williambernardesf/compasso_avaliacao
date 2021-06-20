package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Professor;
import net.javaguides.springboot.repository.ProfessorRepositorio;

@Service
public class ProfessorFuncaoImp implements ProfessorFuncao{
	
	@Autowired
	private ProfessorRepositorio professorRepositorio;

	@Override
	public List<Professor> listaProfessores() {
		return professorRepositorio.findAll();
	}

	@Override
	public void registraProfessor(Professor professor) {
		this.professorRepositorio.save(professor);
	}

	@Override
	public Professor listaProfessorId(long id) {
		Optional<Professor> optional = professorRepositorio.findById(id);
		Professor professor = null;
		if (optional.isPresent()) {
			professor = optional.get();
		} else {
			throw new RuntimeException(" Aluno não encontrado pelo Id :: " + id);
		}
		return professor;
	}

	@Override
	public void excluiProfessorId(long id) {
		this.professorRepositorio.deleteById(id);
	}

	@Override
	public Page<Professor> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.professorRepositorio.findAll(pageable);
	}
}
