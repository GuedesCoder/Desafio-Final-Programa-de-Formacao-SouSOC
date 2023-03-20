package br.com.guedes.ageTechnologyChallenge.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.ageTechnologyChallenge.entities.Exame;
import br.com.guedes.ageTechnologyChallenge.entities.ExamesRealizados;
import br.com.guedes.ageTechnologyChallenge.entities.Funcionario;

@Transactional
public interface ExamesRealizadosRepository extends JpaRepository<ExamesRealizados, Long> {

	List<ExamesRealizados> findAllByOrderByDataRealizacaoDesc();

	@Query("SELECT e FROM ExamesRealizados e WHERE e.funcionario.id = ?1")
	List<ExamesRealizados> findByFuncionarioId(Long funcionarioId);

	@Query("SELECT e FROM ExamesRealizados e WHERE e.exame.id = ?1")
	List<ExamesRealizados> findByExameId(Long exameId);

	@Query("SELECT e FROM ExamesRealizados e WHERE e.dataRealizacao = ?1")
	List<ExamesRealizados> findByDataRealizacao(LocalDate dataRealizacao);

	List<ExamesRealizados> findByFuncionario(Funcionario funcionario);

	List<ExamesRealizados> findByFuncionarioAndExame(Funcionario funcionario, Exame exame);

    List<ExamesRealizados> findByFuncionarioNome(String nome);

}
