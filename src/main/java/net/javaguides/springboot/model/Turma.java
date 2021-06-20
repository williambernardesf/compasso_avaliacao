package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "turmas")
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "cod")
	private String cod;
	
	@Column(name = "codcurso")
	private String codcurso;
	
	@Column(name = "codprof")
	private String codprof;
	
	@Column(name = "codaluno")
	private String codaluno;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getCodcurso() {
		return codcurso;
	}

	public void setCodcurso(String codcurso) {
		this.codcurso = codcurso;
	}

	public String getCodprof() {
		return codprof;
	}

	public void setCodprof(String codprof) {
		this.codprof = codprof;
	}

	public String getCodaluno() {
		return codaluno;
	}

	public void setCodaluno(String codaluno) {
		this.codaluno = codaluno;
	}

	
}