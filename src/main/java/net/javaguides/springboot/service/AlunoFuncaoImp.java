package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Aluno;
import net.javaguides.springboot.repository.AlunoRepositorio;

@Service
public class AlunoFuncaoImp implements AlunoFuncao {

	@Autowired
	private AlunoRepositorio alunoRepositorio;

	@Override
	public List<Aluno> listaAlunos() {
		return alunoRepositorio.findAll();
	}

	@Override
	public void registraAluno(Aluno aluno) {
		this.alunoRepositorio.save(aluno);
	}

	@Override
	public Aluno listaAlunoId(long id) {
		Optional<Aluno> optional = alunoRepositorio.findById(id);
		Aluno aluno = null;
		if (optional.isPresent()) {
			aluno = optional.get();
		} else {
			throw new RuntimeException(" Aluno não encontrado pelo Id :: " + id);
		}
		return aluno;
	}

	@Override
	public void excluiAlunoId(long id) {
		this.alunoRepositorio.deleteById(id);
	}

	@Override
	public Page<Aluno> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.alunoRepositorio.findAll(pageable);
	}
}
