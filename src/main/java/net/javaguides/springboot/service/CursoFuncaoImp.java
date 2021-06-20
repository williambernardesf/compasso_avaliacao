package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Curso;
import net.javaguides.springboot.repository.CursoRepositorio;

@Service
public class CursoFuncaoImp implements CursoFuncao{
	
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	@Override
	public List<Curso> listaCursos() {
		return cursoRepositorio.findAll();
	}

	@Override
	public void registraCurso(Curso curso) {
		this.cursoRepositorio.save(curso);
	}
	
	@Override
	public Curso listaCursoId(long id) {
		Optional<Curso> optional = cursoRepositorio.findById(id);
		Curso curso = null;
		if (optional.isPresent()) {
			curso = optional.get();
		} else {
			throw new RuntimeException(" Curso não encontrado pelo Id :: " + id);
		}
		return curso;
	}

	@Override
	public void excluiCursoId(long id) {
		this.cursoRepositorio.deleteById(id);
	}
	
	@Override
	public Page<Curso> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.cursoRepositorio.findAll(pageable);
	}
}