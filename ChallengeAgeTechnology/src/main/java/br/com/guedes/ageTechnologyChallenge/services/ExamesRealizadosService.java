package br.com.guedes.ageTechnologyChallenge.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.ageTechnologyChallenge.entities.Exame;
import br.com.guedes.ageTechnologyChallenge.entities.ExamesRealizados;
import br.com.guedes.ageTechnologyChallenge.entities.Funcionario;
import br.com.guedes.ageTechnologyChallenge.repositories.ExameRepository;
import br.com.guedes.ageTechnologyChallenge.repositories.ExamesRealizadosRepository;
import br.com.guedes.ageTechnologyChallenge.repositories.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ExamesRealizadosService {

	private final ExamesRealizadosRepository examesRealizadosRepository;
	private final FuncionarioRepository funcionarioRepository;
	private final ExameRepository exameRepository;

	public ExamesRealizadosService(ExamesRealizadosRepository examesRealizadosRepository,
			FuncionarioRepository funcionarioRepository, ExameRepository exameRepository) {
		this.examesRealizadosRepository = examesRealizadosRepository;
		this.funcionarioRepository = funcionarioRepository;
		this.exameRepository = exameRepository;
	}

	@Transactional
	public void save(ExamesRealizados examesRealizados) {
		Funcionario funcionario = funcionarioRepository.findById(examesRealizados.getFuncionario().getId())
				.orElseThrow(() -> new EntityNotFoundException("Funcionario não encontrado"));

		Exame exame = exameRepository.findById(examesRealizados.getExame().getId())
				.orElseThrow(() -> new EntityNotFoundException("Exame não encontrado"));

		examesRealizados.setFuncionario(funcionario);
		examesRealizados.setExame(exame);

		examesRealizadosRepository.save(examesRealizados);
	}

	@Transactional
	public void update(Long id, ExamesRealizados examesRealizadosAtualizado) {
		ExamesRealizados examesRealizados = examesRealizadosRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Exame realizado não encontrado"));

		Funcionario funcionario = funcionarioRepository.findById(examesRealizadosAtualizado.getFuncionario().getId())
				.orElseThrow(() -> new EntityNotFoundException("Funcionario não encontrado"));

		Exame exame = exameRepository.findById(examesRealizadosAtualizado.getExame().getId())
				.orElseThrow(() -> new EntityNotFoundException("Exame não encontrado"));

		examesRealizados.setFuncionario(funcionario);
		examesRealizados.setExame(exame);
		examesRealizados.setDataRealizacao(examesRealizadosAtualizado.getDataRealizacao());

		examesRealizadosRepository.save(examesRealizados);
	}

	public ExamesRealizados findById(Long id) {
		return examesRealizadosRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Exame realizado não encontrado"));
	}

	@Transactional
	public void delete(Long id) {
		examesRealizadosRepository.deleteById(id);
	}

	public List<Funcionario> findAllemployees() {
	    return funcionarioRepository.findAll();
	  }
	
	public List<ExamesRealizados> findAll() {
		return examesRealizadosRepository.findAll();
	}

	public List<ExamesRealizados> findByWorkerId(Long idFuncionario) {
		return examesRealizadosRepository.findByFuncionarioId(idFuncionario);
	}

	public List<ExamesRealizados> buscarPorExameId(Long idExame) {
		return examesRealizadosRepository.findByExameId(idExame);
	}

	public List<ExamesRealizados> findByFuncionarioAndExame(Funcionario funcionario, Exame exame) {
	    return examesRealizadosRepository.findByFuncionarioAndExame(funcionario, exame);
	}

	public List<ExamesRealizados> findByFuncionario(Funcionario funcionario) {
	    return examesRealizadosRepository.findByFuncionarioId(funcionario.getId());
	}


}