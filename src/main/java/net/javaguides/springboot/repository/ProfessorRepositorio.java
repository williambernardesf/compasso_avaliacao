package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Professor;

@Repository
public interface ProfessorRepositorio extends JpaRepository<Professor, Long>{
}