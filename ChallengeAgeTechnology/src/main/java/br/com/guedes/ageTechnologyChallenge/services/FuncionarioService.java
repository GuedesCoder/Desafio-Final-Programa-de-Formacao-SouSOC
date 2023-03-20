package br.com.guedes.ageTechnologyChallenge.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.ageTechnologyChallenge.entities.Funcionario;
import br.com.guedes.ageTechnologyChallenge.repositories.FuncionarioRepository;

@Service
@Transactional
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id).orElse(null);
    }

    public List<Funcionario> findByName(String nome) {
        return funcionarioRepository.findByNomeContainingIgnoreCase(nome);
    }
}

