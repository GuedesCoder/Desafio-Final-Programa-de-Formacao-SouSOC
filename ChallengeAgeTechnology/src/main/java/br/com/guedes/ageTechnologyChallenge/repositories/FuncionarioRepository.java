package br.com.guedes.ageTechnologyChallenge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guedes.ageTechnologyChallenge.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	List<Funcionario> findByNomeContainingIgnoreCase(String nome);

}
