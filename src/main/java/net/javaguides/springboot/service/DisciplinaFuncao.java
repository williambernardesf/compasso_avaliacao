package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Disciplina;

public interface DisciplinaFuncao {
	List<Disciplina> listaDisciplinas();
	void registraDisciplina(Disciplina disciplina);
	Disciplina listaDisciplinaId(long id);
	void excluiDisciplinaId(long id);
	Page<Disciplina> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}