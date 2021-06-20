package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Turma;

public interface TurmaFuncao {
	List<Turma> listaTurma();
	void registraTurma(Turma turma);
	Turma listaTurmaId(long id);
	void excluiTurmaId(long id);
	Page<Turma> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
