package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Disciplina;

@Repository
public interface DisciplinaRepositorio extends JpaRepository<Disciplina, Long>{
}