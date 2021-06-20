package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Professor;

public interface ProfessorFuncao {
	List<Professor> listaProfessores();
	void registraProfessor(Professor professor);
	Professor listaProfessorId(long id);
	void excluiProfessorId(long id);
	Page<Professor> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
