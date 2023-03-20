package br.com.guedes.ageTechnologyChallenge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.ageTechnologyChallenge.entities.Exame;
import br.com.guedes.ageTechnologyChallenge.repositories.ExameRepository;

@Service
@Transactional
public class ExameService {

    @Autowired
    private ExameRepository exameRepository;

    public List<Exame> findAll() {
        return exameRepository.findAll();
    }

    public Exame findById(Long id) {
        return exameRepository.findById(id).orElse(null);
    }

    public List<Exame> findByName(String nome) {
        return exameRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Exame save(Exame exame) {
        return exameRepository.save(exame);
    }

    public void deleta(Long id) {
        exameRepository.deleteById(id);
    }
}
