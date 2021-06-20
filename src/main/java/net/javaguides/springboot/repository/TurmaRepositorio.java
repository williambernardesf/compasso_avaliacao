package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Turma;

@Repository
public interface TurmaRepositorio extends JpaRepository<Turma, Long>{

}
