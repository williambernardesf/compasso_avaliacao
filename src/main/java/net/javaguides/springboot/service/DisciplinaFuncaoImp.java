package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Disciplina;
import net.javaguides.springboot.repository.DisciplinaRepositorio;

@Service
public class DisciplinaFuncaoImp implements DisciplinaFuncao{
	
	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;
	
	@Override
	public List<Disciplina> listaDisciplinas() {
		return disciplinaRepositorio.findAll();
	}

	@Override
	public void registraDisciplina(Disciplina disciplina) {
		this.disciplinaRepositorio.save(disciplina);
	}

	@Override
	public Disciplina listaDisciplinaId(long id) {
		Optional<Disciplina> optional = disciplinaRepositorio.findById(id);
		Disciplina disciplina = null;
		if (optional.isPresent()) {
			disciplina = optional.get();
		} else {
			throw new RuntimeException(" Disciplina não encontrada pelo Id :: " + id);
		}
		return disciplina;
	}

	@Override
	public void excluiDisciplinaId(long id) {
		this.disciplinaRepositorio.deleteById(id);
	}

	@Override
	public Page<Disciplina> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.disciplinaRepositorio.findAll(pageable);
	}

}
