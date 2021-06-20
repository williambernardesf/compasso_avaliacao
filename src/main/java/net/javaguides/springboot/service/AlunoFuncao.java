package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Aluno;

public interface AlunoFuncao {
	List<Aluno> listaAlunos();
	void registraAluno(Aluno aluno);
	Aluno listaAlunoId(long id);
	void excluiAlunoId(long id);
	Page<Aluno> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}