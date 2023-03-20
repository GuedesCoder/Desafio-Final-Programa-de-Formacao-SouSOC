package br.com.guedes.ageTechnologyChallenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.guedes.ageTechnologyChallenge.entities.Funcionario;
import br.com.guedes.ageTechnologyChallenge.services.FuncionarioService;

@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/listar-funcionarios")
    public String listarFuncionarios(Model model) {
        List<Funcionario> funcionarios = funcionarioService.findAll();
        model.addAttribute("funcionarios", funcionarios);
        return "/ViewsFuncionarios/listar-funcionarios";
    }

    @GetMapping("/ver-funcionario/{id}")
    public String verFuncionario(@PathVariable("id") Long id, Model model) {
        Funcionario funcionario = funcionarioService.findById(id);
        model.addAttribute("funcionario", funcionario);
        return "ViewsFuncionarios/ver-funcionario";
    }

    @GetMapping("/novo-funcionario")
    public String novoFuncionario(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "ViewsFuncionarios/novo-funcionario";
    }

    @PostMapping("/salvar-funcionario")
    public String salvarFuncionario(@ModelAttribute("funcionario") Funcionario funcionario, BindingResult result) {
        if (result.hasErrors()) {
            return "ViewsFuncionarios/novo-funcionario";
        }
        funcionarioService.save(funcionario);
        return "redirect:/listar-funcionarios";
    }

    @GetMapping("/atualizar-funcionario/{id}")
    public String atualizarFuncionario(@PathVariable("id") Long id, Model model) {
        Funcionario funcionario = funcionarioService.findById(id);
        model.addAttribute("funcionario", funcionario);
        return "ViewsFuncionarios/atualizar-funcionario";
    }

    @PostMapping("/atualizar-funcionario")
    public String atualizarFuncionario(@ModelAttribute("funcionario") Funcionario funcionario, BindingResult result) {
        if (result.hasErrors()) {
            return "/ViewsFuncionarios/atualizar-funcionario";
        }
        funcionarioService.save(funcionario);
        return "redirect:/listar-funcionarios";
    }

    @GetMapping("/deletar-funcionario/{id}")
    public String deletarFuncionario(@PathVariable("id") Long id) {
        funcionarioService.delete(id);
        return "redirect:/listar-funcionarios";
    }

}
