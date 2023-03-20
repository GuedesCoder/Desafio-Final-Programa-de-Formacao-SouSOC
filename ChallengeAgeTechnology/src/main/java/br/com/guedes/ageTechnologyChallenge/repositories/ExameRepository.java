package br.com.guedes.ageTechnologyChallenge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guedes.ageTechnologyChallenge.entities.Exame;

public interface ExameRepository extends JpaRepository<Exame, Long> {

	List<Exame> findByNomeContainingIgnoreCase(String nome);

}
