package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Curso;

public interface CursoFuncao {
	List<Curso> listaCursos();
	void registraCurso(Curso curso);
	Curso listaCursoId(long id);
	void excluiCursoId(long id);
	Page<Curso> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}