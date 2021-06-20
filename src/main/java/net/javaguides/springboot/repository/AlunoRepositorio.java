package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Aluno;

@Repository
public interface AlunoRepositorio extends JpaRepository<Aluno, Long>{

}