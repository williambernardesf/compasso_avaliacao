package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Turma;
import net.javaguides.springboot.repository.TurmaRepositorio;

@Service
public class TurmaFuncaoImp implements TurmaFuncao{
	
	@Autowired
	private TurmaRepositorio turmaRepositorio;
	
	@Override
	public List<Turma> listaTurma() {
		return turmaRepositorio.findAll();
	}
	
	@Override
	public void registraTurma(Turma turma) {
		this.turmaRepositorio.save(turma);
	}
	
	@Override
	public Turma listaTurmaId(long id) {
		Optional<Turma> optional = turmaRepositorio.findById(id);
		Turma turma = null;
		if (optional.isPresent()) {
			turma = optional.get();
		} else {
			throw new RuntimeException(" Turma não encontrada pelo Id :: " + id);
		}
		return turma;
	}
	
	@Override
	public void excluiTurmaId(long id) {
		this.turmaRepositorio.deleteById(id);
	}
	
	@Override
	public Page<Turma> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.turmaRepositorio.findAll(pageable);
	}

}
